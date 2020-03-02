package com.casc.sczd.mapper;

import com.casc.sczd.bean.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理
 * 
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-19 10:04:16
 */
@Mapper
public interface TestMapper {
    public int save(Test test) ;
    public int update(Test test) ;
    public int deleteByIds(@Param("idList" ) List<Integer> idList);
    public Test getById(int id) ;
    public List<Test> getAll() ;
	
}
