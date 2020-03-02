package com.casc.sczd.service;

import com.casc.sczd.bean.SysRole;
import com.casc.sczd.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 17:26:16
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class SysRoleService{

    @Autowired
    RoleMapper sysRoleMapper;

    /**
     * 保存
     */
    public boolean save(SysRole sysRole){
            sysRoleMapper.save(sysRole);
        return true;
    }

    /**
     * 刪除多條記錄
     */
    public boolean deleteByIds(List<Long> ids)
    {
            sysRoleMapper.deleteByIds(ids);
        return true;
    }

    /**
     * 更新
     */
    public boolean update(SysRole sysRole){
            sysRoleMapper.update(sysRole);
        return true;
    }

    /**
     * 獲取單條信息
     */
    public SysRole getById(long id){
        return sysRoleMapper.getById(id);
    }

    /**
     * 獲取所有
     */
    public List<SysRole> getAll(Integer page, Integer limit){
        PageHelper.startPage(page, limit);//设置数据库分页查询的范围
        return sysRoleMapper.getAll();
    }
}

