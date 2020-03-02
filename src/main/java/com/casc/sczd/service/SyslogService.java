package com.casc.sczd.service;

import com.casc.sczd.bean.SysLog;
import com.casc.sczd.mapper.SyslogMapper;
import com.casc.sczd.util.PageUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 10:22:02
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class SyslogService{

    @Autowired
    SyslogMapper syslogMapper;

    /**
     * 保存
     */
    public boolean save(SysLog syslog){
            syslogMapper.save(syslog);
        return true;
    }

    /**
     * 刪除多條記錄
     */
    public boolean deleteByIds(List<Integer> ids)
    {
            syslogMapper.deleteByIds(ids);
        return true;
    }


    /**
     * 獲取所有
     */
    public List<SysLog> getAll(Integer page, Integer limit){
        PageHelper.startPage(page, limit);//设置数据库分页查询的范围
        return  syslogMapper.getAll();
    }
}

