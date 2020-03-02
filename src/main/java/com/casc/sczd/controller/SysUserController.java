package com.casc.sczd.controller;

import java.util.Arrays;
import java.util.List;

import com.casc.sczd.annotation.SysLogAnnotation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casc.sczd.bean.SysUser;
import com.casc.sczd.service.SysUserService;
import com.casc.sczd.constant.ReturnData;



/**
 * 用户表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 17:26:16
 */
@RestController
@RequestMapping("/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表，獲取所有SysUser記錄
     */
    @GetMapping("/list/{page}/{limit}")
    public ReturnData list(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        List<SysUser> list = sysUserService.getAll(page,limit);
        PageInfo<SysUser> pageInfo=new PageInfo<SysUser>(list);
        return  ReturnData.buildSuccess(pageInfo);
    }


    /**
     * 根據ID獲取單條信息
     */
    @RequestMapping("/getById/{id}")
    public ReturnData getById(@PathVariable("id") Long id){
		SysUser sysUser = sysUserService.getById(id);

        return ReturnData.buildSuccess(sysUser);
    }

    /**
     * 保存
     */
    @SysLogAnnotation("保存数据")
    @PostMapping("/save")
    public ReturnData save(@RequestBody SysUser sysUser){
        sysUser.setUserPassword(new SimpleHash("md5", sysUser.getUserPassword(), null, 2).toString());
		sysUserService.save(sysUser);

        return ReturnData.buildSuccess();
    }

    /**
     * 修改   
     */
    @SysLogAnnotation("更新数据")
    @PostMapping("/update")
    public ReturnData update(@RequestBody SysUser sysUser){
		sysUserService.update(sysUser);
        return ReturnData.buildSuccess();
    }

    /**
     * 删除
     */
    @SysLogAnnotation("删除数据")
    @PostMapping("/delete")
    public ReturnData delete(@RequestBody Long[] ids){
		sysUserService.deleteByIds(Arrays.asList(ids));
        return ReturnData.buildSuccess();
    }

}
