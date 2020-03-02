package com.casc.sczd;

import com.alibaba.druid.sql.visitor.functions.Ascii;
import com.alibaba.druid.util.StringUtils;
import com.mchange.lang.CharUtils;
import com.sun.xml.internal.ws.util.ASCIIUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement//开启事务管理
public class SczdApplication {
    @Autowired
    private RedisTemplate redisTemplate;
    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        redisTemplate.afterPropertiesSet();
    }
    //http://localhost:8000/druid/index.html
    public static void main(String[] args) {
        SpringApplication.run(SczdApplication.class, args);

    }}








