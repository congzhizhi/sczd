package com.casc.sczd.redis;

import com.casc.sczd.constant.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
    @Autowired
    RedisTemplate redisTemplate;

    @Resource
    Global global;

    //shiro用户角色权限在redis中的缓存键
    private static String key_prex = "shiro:cache:com.casc.sczd.shiro.CustomRealm.authorizationCache:";

    //当用户权限更改时，删除shiro中的用户权限缓存
    public void deleteCache(String username) {
        if (global.isRedis()) {
            String usernamekey = key_prex + username;
            System.out.println(usernamekey);
            if (redisTemplate.hasKey(usernamekey)) {
                redisTemplate.delete(usernamekey);
            }
        }
    }


    private static double size = Math.pow(2, 32);


    /**
     * 写入缓存
     *
     * @param key
     * @param offset 位 8Bit=1Byte
     * @return
     */
    public boolean setBit(String key, long offset, boolean isShow) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.setBit(key, offset, isShow);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param offset
     * @return
     */
    public boolean getBit(String key, long offset) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.getBit(key, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        if (global.isRedis()) {
            try {
                ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
                operations.set(key, value);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        if (global.isRedis()) {
            try {
                ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
                operations.set(key, value);
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        if (global.isRedis()) {
            for (String key : keys) {
                remove(key);
            }
        }
    }


    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (global.isRedis()) {
            if (exists(key)) {
                redisTemplate.delete(key);
            }
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        if (global.isRedis()) {
            return redisTemplate.hasKey(key);
        } else {
            return false;
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        if (global.isRedis()) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        }
        return result;
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value) {
        if (global.isRedis()) {
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            hash.put(key, hashKey, value);
        }
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) {
        if (global.isRedis()) {
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            return hash.get(key, hashKey);
        } else {
            return null;
        }
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) {
        if (global.isRedis()) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            list.rightPush(k, v);
        }
    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) {
        if (global.isRedis()) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            return list.range(k, l, l1);
        } else {
            return null;
        }
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        if (global.isRedis()) {
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            set.add(key, value);
        }
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) {
        if (global.isRedis()) {
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            return set.members(key);
        } else {
            return null;
        }
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            zset.add(key, value, scoure);
        }
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            redisTemplate.opsForValue();
            return zset.rangeByScore(key, scoure, scoure1);
        } else {
            return null;
        }
    }


    //第一次加载的时候将数据加载到redis中
    public void saveDataToRedis(String name) {
        if (global.isRedis()) {

            double index = Math.abs(name.hashCode() % size);
            long indexLong = new Double(index).longValue();
            boolean availableUsers = setBit("availableUsers", indexLong, true);
        }
    }

    //第一次加载的时候将数据加载到redis中
    public boolean getDataToRedis(String name) {
        if (global.isRedis()) {
            double index = Math.abs(name.hashCode() % size);
            long indexLong = new Double(index).longValue();
            return getBit("availableUsers", indexLong);
        } else {
            return false;
        }
    }

    /**
     * 有序集合获取排名
     *
     * @param key   集合名称
     * @param value 值
     */
    public Long zRank(String key, Object value) {
        if (global.isRedis()) {

            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.rank(key, value);
        } else {
            return null;
        }
    }


    /**
     * 有序集合获取排名
     *
     * @param key
     */
    public Set<ZSetOperations.TypedTuple<Object>> zRankWithScore(String key, long start, long end) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            Set<ZSetOperations.TypedTuple<Object>> ret = zset.rangeWithScores(key, start, end);
            return ret;
        } else {
            return null;
        }
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     */
    public Double zSetScore(String key, Object value) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.score(key, value);
        } else {
            return null;
        }
    }


    /**
     * 有序集合添加分数
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void incrementScore(String key, Object value, double scoure) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            zset.incrementScore(key, value, scoure);
        }
    }


    /**
     * 有序集合获取排名
     *
     * @param key
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithScore(String key, long start, long end) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            Set<ZSetOperations.TypedTuple<Object>> ret = zset.reverseRangeByScoreWithScores(key, start, end);
            return ret;
        } else {
            return null;
        }
    }

    /**
     * 有序集合获取排名
     *
     * @param key
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithRank(String key, long start, long end) {
        if (global.isRedis()) {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            Set<ZSetOperations.TypedTuple<Object>> ret = zset.reverseRangeWithScores(key, start, end);
            return ret;
        } else {
            return null;
        }
    }


}
