package com.casc.sczd;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试用例执行顺序
 *
 * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
 */
public class QuickStartTest4_2 {
    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
    @Before
    public void init() {
        //初始化数据源,实际是要从数据库读取的
        accountRealm.addAccount("xdclass", "123","root","admin");
        accountRealm.addAccount("jack", "456","admin");
        //构建环境
        defaultSecurityManager.setRealm(accountRealm);
    }

    /**
     * 登录认证
     */
    @Test
    public void testAuthentication() {
        try {
            //设置验证运行环境
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            //当前操作主体， application user
            Subject subject = SecurityUtils.getSubject();
            //包装用户输入的账号密码
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken("jack", "456");
            //登录验证
            subject.login(usernamePasswordToken);
            //验证结果
            System.out.println("认证结果:"+subject.isAuthenticated());
            System.out.println("权限验证:"+subject.hasRole("admin"));
            System.out.println("登录账号"+subject.getPrincipal());
        } catch (AuthenticationException e) {
            System.out.println(e.getClass().getSimpleName());
        }
    }
}
