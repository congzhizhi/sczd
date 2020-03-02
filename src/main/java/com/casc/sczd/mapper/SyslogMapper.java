package com.casc.sczd.mapper;

import com.casc.sczd.bean.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 系统日志
 * 
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 10:22:02
 */
@Mapper
public interface SyslogMapper  {
    public int save(SysLog syslog) ;
    public int deleteByIds(@Param("idList") List<Integer> idList);
    public List<SysLog> getAll() ;
	
}
