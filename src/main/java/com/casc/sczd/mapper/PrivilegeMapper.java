package com.casc.sczd.mapper;

import java.util.List;

import com.casc.sczd.bean.SysPrivilege;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
@Mapper
public interface PrivilegeMapper {

	public int save(SysPrivilege sysPrivilege) ;
	public int update(SysPrivilege sysPrivilege) ;
	public int deleteByIds(@Param("idList")List<Long> idList);
	public SysPrivilege getById(long id) ;
	public List<SysPrivilege> getAll() ;

	public List<SysPrivilege> getPrivilegeByRoleId(long roleId);

}
