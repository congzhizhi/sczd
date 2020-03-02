package com.casc.sczd.controller;

import java.util.Arrays;
import java.util.List;

import com.casc.sczd.annotation.SysLogAnnotation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casc.sczd.bean.Errorlog;
import com.casc.sczd.service.ErrorlogService;
import com.casc.sczd.constant.ReturnData;



/**
 * 
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-27 09:43:45
 */
@RestController
@RequestMapping("/errorlog")
public class ErrorlogController {
    @Autowired
    private ErrorlogService errorlogService;

    /**
     * 列表，獲取所有Errorlog記錄
     */
    @GetMapping("/list/{page}/{limit}")
    public ReturnData list(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        List<Errorlog> list = errorlogService.getAll(page,limit);
        PageInfo<Errorlog> pageInfo=new PageInfo<Errorlog>(list);
        return  ReturnData.buildSuccess(pageInfo);
    }


    /**
     * 根據ID獲取單條信息
     */
    @RequestMapping("/getById/{id}")
    public ReturnData getById(@PathVariable("id") Long id){
		Errorlog errorlog = errorlogService.getById(id);

        return ReturnData.buildSuccess(errorlog);
    }

    /**
     * 保存
     */
    @SysLogAnnotation("保存数据")
    @PostMapping("/save")
    public ReturnData save(@RequestBody Errorlog errorlog){
		errorlogService.save(errorlog);

        return ReturnData.buildSuccess();
    }

    /**
     * 修改
     */
    @SysLogAnnotation("更新数据")
    @PostMapping("/update")
    public ReturnData update(@RequestBody Errorlog errorlog){
		errorlogService.update(errorlog);
        return ReturnData.buildSuccess();
    }

    /**
     * 删除
     */
    @SysLogAnnotation("删除数据")
    @PostMapping("/delete")
    public ReturnData delete(@RequestBody Long[] ids){
		errorlogService.deleteByIds(Arrays.asList(ids));
        return ReturnData.buildSuccess();
    }

}
