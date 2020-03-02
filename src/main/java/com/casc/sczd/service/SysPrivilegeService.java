package com.casc.sczd.service;

import com.casc.sczd.bean.SysPrivilege;
import com.casc.sczd.mapper.PrivilegeMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 权限表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 17:08:24
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class SysPrivilegeService{

    @Autowired
    PrivilegeMapper sysPrivilegeMapper;

    /**
     * 保存
     */
    public boolean save(SysPrivilege sysPrivilege){
        sysPrivilegeMapper.save(sysPrivilege);
        return true;
    }

    /**
     * 刪除多條記錄
     */
    public boolean deleteByIds(List<Long> ids)
    {
        sysPrivilegeMapper.deleteByIds(ids);
        return true;
    }

    /**
     * 更新
     */
    public boolean update(SysPrivilege sysPrivilege){
        sysPrivilegeMapper.update(sysPrivilege);
        return true;
    }

    /**
     * 獲取單條信息
     */
    public SysPrivilege getById(long id){
        return sysPrivilegeMapper.getById(id);
    }

    /**
     * 獲取所有
     */
    public List<SysPrivilege> getAll(Integer page, Integer limit){
        PageHelper.startPage(page, limit);//设置数据库分页查询的范围
        return sysPrivilegeMapper.getAll();
    }
}

