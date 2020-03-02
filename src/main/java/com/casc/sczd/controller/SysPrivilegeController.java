package com.casc.sczd.controller;

import java.util.Arrays;
import java.util.List;

import com.casc.sczd.annotation.SysLogAnnotation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casc.sczd.bean.SysPrivilege;
import com.casc.sczd.service.SysPrivilegeService;
import com.casc.sczd.constant.ReturnData;



/**
 * 权限表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 17:08:24
 */
@RestController
@RequestMapping("/sysprivilege")
public class SysPrivilegeController {
    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    /**
     * 列表，獲取所有SysPrivilege記錄
     */
    @GetMapping("/list/{page}/{limit}")
    public ReturnData list(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        List<SysPrivilege> list = sysPrivilegeService.getAll(page,limit);
        PageInfo<SysPrivilege> pageInfo=new PageInfo<SysPrivilege>(list);
        return  ReturnData.buildSuccess(pageInfo);
    }


    /**
     * 根據ID獲取單條信息
     */
    @RequestMapping("/getById/{id}")
    public ReturnData getById(@PathVariable("id") Long id){
		SysPrivilege sysPrivilege = sysPrivilegeService.getById(id);

        return ReturnData.buildSuccess(sysPrivilege);
    }

    /**
     * 保存
     */
    @SysLogAnnotation("保存数据")
    @PostMapping("/save")
    public ReturnData save(@RequestBody SysPrivilege sysPrivilege){
		sysPrivilegeService.save(sysPrivilege);

        return ReturnData.buildSuccess();
    }

    /**
     * 修改   
     */
    @SysLogAnnotation("更新数据")
    @PostMapping("/update")
    public ReturnData update(@RequestBody SysPrivilege sysPrivilege){
		sysPrivilegeService.update(sysPrivilege);
        return ReturnData.buildSuccess();
    }

    /**
     * 删除
     */
    @SysLogAnnotation("删除数据")
    @PostMapping("/delete")
    public ReturnData delete(@RequestBody Long[] ids){
		sysPrivilegeService.deleteByIds(Arrays.asList(ids));
        return ReturnData.buildSuccess();
    }

}
