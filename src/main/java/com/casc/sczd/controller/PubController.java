package com.casc.sczd.controller;

import com.casc.sczd.annotation.SysLogAnnotation;
import com.casc.sczd.bean.SysUser;
import com.casc.sczd.bean.Test;
import com.casc.sczd.constant.ReturnData;
import com.casc.sczd.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户管理
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-19 10:04:16
 */
@RestController
@RequestMapping("pub")
public class PubController {
    @Autowired
    private TestService testService;

    /**
     * 如果用户没有登录就访问接口，shiro会调用此接口
     */
    @GetMapping("needlogin")
    public ReturnData needlogin(){
        return ReturnData.buildError("温馨提示：请先登录！",-2);
    }

    /**
     * 登录了，但是没有权限，未授权就会调用此方法
     */
    @GetMapping("notpermit")
    public ReturnData notpermit(){
        return ReturnData.buildError("温馨提示：您还未获取该操作权限！",-3);
    }

    /**
     * 登录接口
     * @param sysUser
     * @param request
     * @param response
     * @return
     */
    @PostMapping("login")
    public ReturnData login(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getUserPassword());
            subject.login(usernamePasswordToken);

            return ReturnData.buildSuccess(subject.getSession().getId(),"登录成功");

        }catch (Exception e){
            e.printStackTrace();

            return ReturnData.buildError("账号或者密码错误");

        }


    }
}
