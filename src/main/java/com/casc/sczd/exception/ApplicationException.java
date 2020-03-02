package com.casc.sczd.exception;


import com.casc.sczd.bean.Errorlog;
import com.casc.sczd.mapper.ErrorlogMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *应用级异常
 */
@ControllerAdvice
public class ApplicationException {


    @Autowired
    ErrorlogMapper errorlogMapper;

    @ExceptionHandler(Exception.class)
    public void exception(Exception e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        response.setContentType ("text/html;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();//创建一个根对象
        root.put("code", -1);
        root.put("msg", "应用出错:"+e.getMessage());
        root.put("level", "应用级异常");

        Errorlog errorlog = new Errorlog();
        errorlog.setDesp("应用出错："+e.getMessage());
        errorlog.setType(2);
        errorlog.setTime(new Date());
        errorlogMapper.save(errorlog);

        PrintWriter out = response.getWriter();
        out.write(root.toString());
        out.flush();
        out.close();
    }
}