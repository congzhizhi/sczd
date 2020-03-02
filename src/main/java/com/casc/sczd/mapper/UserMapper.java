package com.casc.sczd.mapper;

import java.util.List;
import java.util.Map;

import com.casc.sczd.bean.SysRole;
import com.casc.sczd.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public int save(SysUser sysUser) ;
    public int update(SysUser sysUser) ;
    public int deleteByIds(@Param("idList")List<Long> idList);
    public SysUser getById(long id) ;
    public List<SysUser> getAll() ;
    public SysUser getByUserName(String userName);
    public SysUser getByUsernameAndPwd(@Param("userName")String userName,@Param("password")String password);

    /**
     * 查询用户信息及关联的角色和权限
     * @param userid
     * @return
     */
    public SysUser getRolesAndPrivilegesByUser(Long userid);
    /**
     * 查询用户信息及关联的角色和权限
     * @param username
     * @return
     */
    public SysUser getRolesAndPrivilegesByUsername(String username);

    /**
     * 通过 id 查询用户
     *
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查询全部用户
     *
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户 id 获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 新增用户,返回的不是主键值，而是影响的行数
     *
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户 - 使用 useGeneratedKeys,返回主键值的ID值复制在 sysUser对象的id中
     *
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户 - 使用 selectKey 方式，返回主键值的ID值复制在 sysUser对象的id中，其实用insert2就行
     *
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);



    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);


    /**
     * 通过 Map 更新列
     *
     * @param map
     * @return
     */
    int updateByMap(Map<String, Object> map);



    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(SysUser sysUser);

    /**
     * 根据用户 id 和 角色的 enabled 状态获取用户的角色
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId")Long userId, @Param("enabled")Integer enabled);
/*	当只有一个参数（基本类型或拥有 Type Handler 配置的
	类型）的时候，为什么可以不使用注解？这是因为在这种情况下（除集合和数组外） ， MyBatis
	不关心这个参数叫什么名字就会直接把这个唯一的参数值拿来使用。*/

    /**
     * 根据用户 id 和 角色的 enabled 状态获取用户的角色
     *
     * @param user
     * @param role
     * @return
     */
    List<SysRole> selectRolesByUserAndRole(@Param("user")SysUser user, @Param("role")SysRole role);



    /**
     * 根据动态条件查询用户信息
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);


    /**
     * 根据用户 id 或用户名查询  choose when otherwise
     *
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);



    /**
     * 根据用户 id 集合查询 foreach用法
     *
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(@Param("idList")List<Long> idList);



    /**
     * 批量插入用户信息
     *
     * @param userList
     * @return
     */
    int insertList(@Param("userList")List<SysUser> userList);


/**
 * 关系映射查询，一对一，一对多，多对多********************************************************
 */


    /**
     * 根据用户 id 获取用户信息和用户的角色信息,一对一映射
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * 根据用户 id 获取用户信息和用户的角色信息   一对一映射
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById2(Long id);



    /**
     * 根据用户 id 获取用户信息和用户的角色信息，嵌套查询方式  延迟加载  一对一映射
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);


    /**
     * 获取所有的用户以及对应的所有角色   一对多映射
     *
     * @return
     */
    List<SysUser> selectAllUserAndRoles();






















}
