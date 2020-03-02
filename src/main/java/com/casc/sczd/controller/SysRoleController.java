package com.casc.sczd.controller;

import java.util.Arrays;
import java.util.List;

import com.casc.sczd.annotation.SysLogAnnotation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casc.sczd.bean.SysRole;
import com.casc.sczd.service.SysRoleService;
import com.casc.sczd.constant.ReturnData;



/**
 * 角色表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 17:26:16
 */
@RestController
@RequestMapping("/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表，獲取所有SysRole記錄
     */
    @GetMapping("/list/{page}/{limit}")
    public ReturnData list(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        List<SysRole> list = sysRoleService.getAll(page,limit);
        PageInfo<SysRole> pageInfo=new PageInfo<SysRole>(list);
        return  ReturnData.buildSuccess(pageInfo);
    }


    /**
     * 根據ID獲取單條信息
     */
    @RequestMapping("/getById/{id}")
    public ReturnData getById(@PathVariable("id") Long id){
		SysRole sysRole = sysRoleService.getById(id);

        return ReturnData.buildSuccess(sysRole);
    }

    /**
     * 保存
     */
    @SysLogAnnotation("保存数据")
    @PostMapping("/save")
    public ReturnData save(@RequestBody SysRole sysRole){
		sysRoleService.save(sysRole);

        return ReturnData.buildSuccess();
    }

    /**
     * 修改   
     */
    @SysLogAnnotation("更新数据")
    @PostMapping("/update")
    public ReturnData update(@RequestBody SysRole sysRole){
		sysRoleService.update(sysRole);
        return ReturnData.buildSuccess();
    }

    /**
     * 删除
     */
    @SysLogAnnotation("删除数据")
    @PostMapping("/delete")
    public ReturnData delete(@RequestBody Long[] ids){
		sysRoleService.deleteByIds(Arrays.asList(ids));
        return ReturnData.buildSuccess();
    }

}
