package com.casc.sczd.constant;


import java.io.Serializable;

public class ReturnData implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer code; // 状态码 0 表示成功，-1表示失败,-2表示未登录，-3表示没有权限
    private String msg;// 描述
    private Object data; // 数据

    public ReturnData() {
    }


    public ReturnData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    // 成功，传入数据
    public static ReturnData buildSuccess() {
        return new ReturnData(0, null, null);
    }

    // 成功，传入数据
    public static ReturnData buildSuccess(Object data) {
        return new ReturnData(0, data, null);
    }
    public static ReturnData buildSuccess(long count ,Object data) {
        return new ReturnData(0, data, null);
    }

    // 失败，传入描述信息
    public static ReturnData buildError(String msg) {
        return new ReturnData(-1, null, msg);
    }

    // 失败，传入描述信息,状态码
    public static ReturnData buildError(String msg, Integer code) {
        return new ReturnData(code, null, msg);
    }

    // 成功，传入数据,及描述信息
    public static ReturnData buildSuccess(Object data, String msg) {
        return new ReturnData(0, data, msg);
    }
    // 成功，传入数据,及状态码
    public static ReturnData buildSuccess(Object data, int code) {
        return new ReturnData(code, data, null);
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
                + "]";
    }

}
