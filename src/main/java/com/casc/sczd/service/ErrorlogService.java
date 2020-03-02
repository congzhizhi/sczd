package com.casc.sczd.service;

import com.casc.sczd.bean.Errorlog;
import com.casc.sczd.mapper.ErrorlogMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-27 09:43:45
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class ErrorlogService{

    @Autowired
    ErrorlogMapper errorlogMapper;

    /**
     * 保存
     */
    public boolean save(Errorlog errorlog){
            errorlogMapper.save(errorlog);
        return true;
    }

    /**
     * 刪除多條記錄
     */
    public boolean deleteByIds(List<Long> ids)
    {
            errorlogMapper.deleteByIds(ids);
        return true;
    }

    /**
     * 更新
     */
    public boolean update(Errorlog errorlog){
            errorlogMapper.update(errorlog);
        return true;
    }

    /**
     * 獲取單條信息
     */
    public Errorlog getById(long id){
        return errorlogMapper.getById(id);
    }

    /**
     * 獲取所有
     */
    public List<Errorlog> getAll(Integer page, Integer limit){
        PageHelper.startPage(page, limit);//设置数据库分页查询的范围
        return errorlogMapper.getAll();
    }
}

