package com.casc.sczd.exception;


import com.casc.sczd.bean.Errorlog;
import com.casc.sczd.mapper.ErrorlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

/**
 * 系統級異常
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {

    @Autowired
    ErrorlogMapper errorlogMapper;
    @Override
    public Map<String , Object> getErrorAttributes(WebRequest webRequest , boolean
            includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,
                includeStackTrace);
        //封装成自定义的字段，去掉系统默认字段
        errorAttributes.put("code",errorAttributes.get("status"));
        errorAttributes.put("msg","服务器出错:"+errorAttributes.get("message"));
        errorAttributes.put("level","系统级异常");
        errorAttributes.remove("error");
        errorAttributes.remove("message");
        errorAttributes.remove("status");

        Errorlog errorlog = new Errorlog();
        errorlog.setDesp(""+errorAttributes.get("msg"));
        errorlog.setType(1);
        errorlog.setTime(new Date());
        errorlogMapper.save(errorlog);
        return errorAttributes;
    }
}