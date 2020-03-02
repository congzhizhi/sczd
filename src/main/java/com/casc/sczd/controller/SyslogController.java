package com.casc.sczd.controller;

import java.util.Arrays;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casc.sczd.bean.SysLog;
import com.casc.sczd.service.SyslogService;
import com.casc.sczd.constant.ReturnData;



/**
 * 系统日志
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 10:22:02
 */
@RestController
@RequestMapping("/syslog")
public class SyslogController {
    @Autowired
    private SyslogService syslogService;

    /**
     * 列表，獲取所有Syslog記錄
     */
    @GetMapping("/list/{page}/{limit}")
    public ReturnData list( @PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
//        PageUtils page = syslogService.getAll(param);
        List<SysLog> list = syslogService.getAll(page, limit);
        PageInfo<SysLog> pageInfo=new PageInfo<SysLog>(list);
        return  ReturnData.buildSuccess(pageInfo);
    }



    /**
     * 删除
     */
    @PostMapping("/delete")
    public ReturnData delete(@RequestBody Integer[] ids){
		syslogService.deleteByIds(Arrays.asList(ids));
        return ReturnData.buildSuccess();
    }

}
