package com.casc.sczd.service;

import com.casc.sczd.bean.Test;
import com.casc.sczd.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-19 10:04:16
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class TestService{

    @Autowired
    TestMapper testMapper;

    /**
     * 保存
     */
    public boolean save(Test test){
            testMapper.save(test);
        return true;
    }

    /**
     * 刪除多條記錄
     */
    public boolean deleteByIds(List<Integer> ids)
    {
            testMapper.deleteByIds(ids);
        return true;
    }

    /**
     * 更新
     */
    public boolean update(Test test){
            testMapper.update(test);
        return true;
    }

    /**
     * 獲取單條信息
     */
    public Test getById(int id){
        return testMapper.getById(id);
    }

    /**
     * 獲取所有
     */
    public List<Test> getAll(){
        return testMapper.getAll();
    }
}

