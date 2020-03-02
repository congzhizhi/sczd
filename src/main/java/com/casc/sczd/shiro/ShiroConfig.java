package com.casc.sczd.shiro;

import com.casc.sczd.bean.SysUser;
import com.casc.sczd.constant.Global;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Resource
    Global global;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        System.out.println("执行 ShiroFilterFactoryBean.shiroFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager，也就是开启shiro的运行环境
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //需要登录的接口，如果访问某个接口，需要登录却没登录，则调用此接口(如果不是前后端分离，则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/pub/needlogin");

        //登录成功，跳转url，如果前后端分离，则没这个调用
//        shiroFilterFactoryBean.setSuccessUrl("/");

        //登录了，但是没有权限，未授权就会调用此方法， 先验证登录-》再验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/notpermit");

//        //设置自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter", new CustomRolesOrAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);


        //拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为同学使用的是hashmap, 无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");//直接访问localhost//logout  注意请求头加上token

        //匿名可以访问，也是就游客模式
        filterChainDefinitionMap.put("/pub/**", "anon");
        //匿名可以访问，也是就游客模式
        filterChainDefinitionMap.put("/test/**", "anon");

        //登录用户才可以访问
        filterChainDefinitionMap.put("/authc/**", "authc");

        //管理员角色才可以访问,
        filterChainDefinitionMap.put("/admin/**", "roleOrFilter[admin]");//有其中一个即可

        filterChainDefinitionMap.put("/syslog/**", "perms[/syslog/list]");
        //有编辑权限才可以访问
//        filterChainDefinitionMap.put("/video/update","perms[video_update]");


        //坑二: 过滤链是顺序执行，从上而下，一般讲/** 放到最下面

        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setSessionManager(sessionManager());
        //使用自定义的cacheManager
        if(global.isRedis()){
            securityManager.setCacheManager(cacheManager());
        }
        securityManager.setRealm(customRealm());

        return securityManager;
    }


    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//
//        //设置散列算法：这里使用的MD5算法
        credentialsMatcher.setHashAlgorithmName("md5");
//
//        //散列次数，好比散列2次，相当于md5(md5(xxxx))
        credentialsMatcher.setHashIterations(2);

        return credentialsMatcher;
    }


    @Bean
    public SessionManager sessionManager() {

        CustomSessionManager customSessionManager = new CustomSessionManager();
        //超时时间，默认 30分钟，会话超时；方法里面的单位是毫秒
        customSessionManager.setGlobalSessionTimeout(Long.valueOf(global.getTimeout()));
        //将session持久化到redis
        if(global.isRedis()){
            customSessionManager.setSessionDAO(redisSessionDAO());
        }
        return customSessionManager;
    }


    /**
     * 配置redisManager
     */
    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(global.getRedisip());
        redisManager.setPort(global.getRedisport());
        redisManager.setPassword(global.getRedispassword());
        redisManager.setDatabase(global.getDatabase());
        return redisManager;
    }


    /**
     * 配置具体cache实现类
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间，单位是秒，60s
        redisCacheManager.setExpire(global.getRedistimeout());
        //redis中针对不同用户缓存,如shiro:cache:com.casc.sczd.shiro.CustomRealm.authorizationCache:admin，用户登录名加在最后
        redisCacheManager.setPrincipalIdFieldName("username");
        return redisCacheManager;
    }


    /**
     * 自定义session持久化
     *
     * @return
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        //设置sessionid生成器
        redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }

    /**
     * api controller 层面
     * 加入注解的使用，不加入这个AOP注解不生效(shiro的注解 例如 @RequiresGuest)
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


}
