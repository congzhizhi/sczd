package com.casc.sczd.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="global")
@Data
public class Global {

    //上傳文件保存地址
    private String uploadFileLocation;
    private String timeout;
    private boolean redis;
    private int redistimeout;
    private String redisip;
    private int redisport;
    private String redispassword;
    private int database;
}
