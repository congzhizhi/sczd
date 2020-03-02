package com.casc.sczd.mapper;

import com.casc.sczd.bean.Errorlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-27 09:43:45
 */
@Mapper
public interface ErrorlogMapper {
    public int save(Errorlog errorlog) ;
    public int update(Errorlog errorlog) ;
    public int deleteByIds(@Param("idList") List<Long> idList);
    public Errorlog getById(long id) ;
    public List<Errorlog> getAll() ;
	
}
