/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.casc.sczd.aop;

import com.casc.sczd.annotation.PageHelperAnnotation;
import com.casc.sczd.annotation.SysLogAnnotation;
import com.casc.sczd.bean.SysLog;
import com.casc.sczd.bean.SysUser;
import com.casc.sczd.service.SyslogService;
import com.casc.sczd.util.HttpContextUtils;
import com.casc.sczd.util.IPUtils;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;


/**
 * 分页切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class PageHelperAspect {
    @Autowired
    private SyslogService sysLogService;

    @Pointcut("@annotation(com.casc.sczd.annotation.PageHelperAnnotation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        PageHelperAnnotation pageHelperAnnotation = method.getAnnotation(PageHelperAnnotation.class);
        if (pageHelperAnnotation != null) {
            //请求的参数
            Object[] args = joinPoint.getArgs();
            Map<String, Object> params = (Map<String, Object>) args[0];
            //在service方法执行前，自动进行分页处理
            PageHelper.startPage((int)params.get("page"), (int)params.get("limit")).countColumn("id");//设置数据库分页查询的范围
        }


        //执行方法
        Object result = joinPoint.proceed();


        return result;
    }

}
