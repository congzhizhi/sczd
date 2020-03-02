package com.casc.sczd.controller;

import java.util.Arrays;
import java.util.List;

import com.casc.sczd.annotation.SysLogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casc.sczd.bean.Test;
import com.casc.sczd.service.TestService;
import com.casc.sczd.constant.ReturnData;



/**
 * 用户管理
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-19 10:04:16
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestService testService;


    /**
     * 列表，獲取所有Test記錄
     */
    @GetMapping("list")
    public ReturnData list(){
        List<Test> list = testService.getAll();
        return  ReturnData.buildSuccess(list);
    }


    /**
     * 根據ID獲取單條信息
     */
    @GetMapping("getById/{id}")
    public ReturnData getById(@PathVariable("id") Integer id){
		Test test = testService.getById(id);

        return ReturnData.buildSuccess(test);
    }

    /**
     * 保存
     */
    @SysLogAnnotation("保存用户")
    @PostMapping("save")
    public ReturnData save(

            @RequestBody   Test test){
            testService.save(test);

        return ReturnData.buildSuccess(test);
    }

    /**
     * 修改
     */
    @PostMapping("update")
    public ReturnData update(@RequestBody Test test){
		testService.update(test);
        return ReturnData.buildSuccess();
    }

    /**
     * 删除
     */

    @PostMapping("delete")
    public ReturnData delete(@RequestBody Integer[] ids){
		testService.deleteByIds(Arrays.asList(ids));
        return ReturnData.buildSuccess();
    }

}
