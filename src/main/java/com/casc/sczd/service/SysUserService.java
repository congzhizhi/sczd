package com.casc.sczd.service;

import com.casc.sczd.bean.SysUser;
import com.casc.sczd.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 17:26:16
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class SysUserService{

    @Autowired
    UserMapper sysUserMapper;

    /**
     * 保存
     */
    public boolean save(SysUser sysUser){
            sysUserMapper.save(sysUser);
        return true;
    }

    /**
     * 刪除多條記錄
     */
    public boolean deleteByIds(List<Long> ids)
    {
            sysUserMapper.deleteByIds(ids);
        return true;
    }

    /**
     * 更新
     */
    public boolean update(SysUser sysUser){
            sysUserMapper.update(sysUser);
        return true;
    }

    /**
     * 獲取單條信息
     */
    public SysUser getById(long id){
        return sysUserMapper.getById(id);
    }

    /**
     * 獲取所有
     */
    public List<SysUser> getAll(Integer page, Integer limit){
        PageHelper.startPage(page, limit);//设置数据库分页查询的范围
        return sysUserMapper.getAll();
    }
}

