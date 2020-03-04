package com.casc.sczd.service;

import com.github.pagehelper.PageHelper;

import java.util.Map;

public class BaseService {

    public void startPage(Map<String, Object> params){
            PageHelper.startPage((int)params.get("page"), (int)params.get("limit")).countColumn("id");//设置数据库分页查询的范围
    }

}
