package com.casc.sczd.mapper;

import java.util.List;

import com.casc.sczd.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoleMapper {




    public int save(SysRole sysRole) ;
    public int update(SysRole sysRole) ;
    public int deleteByIds(@Param("idList")List<Long> idList);
    public SysRole getById(long id) ;
    public List<SysRole> getAll() ;
    public List<SysRole> getRolesByUserId(Long userid);



    @Select({
            "select id,role_name roleName, enabled, create_by createBy, create_time createTimefrom from sys_role where id = #{id}" })
    SysRole selectById(Long id);



    public SysRole selectRoleById(Long id);

}