package com.casc.sczd;

import com.alibaba.druid.sql.visitor.functions.Ascii;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.util.StringUtils;
import com.mchange.lang.CharUtils;
import com.sun.xml.internal.ws.util.ASCIIUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement//开启事务管理
public class SczdApplication {
    //http://localhost:8000/druid/index.html
    public static void main(String[] args) {
        SpringApplication.run(SczdApplication.class, args);

    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),  "/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
        registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
//        registrationBean.addInitParameter("loginUsername", "root");
//        registrationBean.addInitParameter("loginPassword", "1234");
        registrationBean.addInitParameter("resetEnable", "false");
        //test
        return registrationBean;
    }
}








