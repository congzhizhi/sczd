package com.casc.sczd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisUtils {
    @Autowired
    RedisTemplate redisTemplate;
    private static  String key_prex="shiro:cache:com.casc.sczd.shiro.CustomRealm.authorizationCache:";

    //当用户权限更改时，删除shiro中的用户权限缓存
    public  void deleteCache(String username){
        String usernamekey = key_prex + username;
        System.out.println(usernamekey);
        if(redisTemplate.hasKey(usernamekey)){
            redisTemplate.delete(usernamekey);
        }
    }
}
