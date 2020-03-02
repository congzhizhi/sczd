package com.casc.sczd.controller;

import com.casc.sczd.bean.SysUser;
import com.casc.sczd.constant.ReturnData;
import com.casc.sczd.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户管理
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-19 10:04:16
 */
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisUtils redisUtil;


    /**
     * 删除
     */

    @PostMapping("delete")
    public ReturnData delete(@RequestBody SysUser sysUser){
        System.out.println("请求结束参数"+sysUser.getUserName());

        redisUtil.deleteCache(sysUser.getUserName());
        System.out.println("请求结束");
        return ReturnData.buildSuccess();
    }

}
