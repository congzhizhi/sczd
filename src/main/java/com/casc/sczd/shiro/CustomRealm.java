package com.casc.sczd.shiro;

import com.casc.sczd.bean.SysPrivilege;
import com.casc.sczd.bean.SysRole;
import com.casc.sczd.bean.SysUser;
import com.casc.sczd.constant.Global;
import com.casc.sczd.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {


    @Autowired
    Global global;
    /**
     * 进行权限校验的时候回调用
     *
     * @param principals
     * @return
     */
    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权认证");
        SysUser newuser = (SysUser) principals.getPrimaryPrincipal();

        SysUser user = userMapper.getRolesAndPrivilegesByUsername(newuser.getUserName());
        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();
        List<SysRole> roleList = user.getRoleList();

        for (SysRole role : roleList) {
            stringRoleList.add(role.getRoleName());

            List<SysPrivilege> permissionList = role.getPrivilegeList();

            for (SysPrivilege p : permissionList) {
                if (p != null) {
                    stringPermissionList.add(p.getPrivilegeUrl());
                }
            }

        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录的时候会调用
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("登录认证");

        //从token获取用户信息，token代表用户输入
        String username = (String) token.getPrincipal();
        SysUser user = userMapper.getRolesAndPrivilegesByUsername(username);


        //取密码
        String pwd = user.getUserPassword();
        if (pwd == null || "".equals(pwd)) {
            return null;
        }

            return new SimpleAuthenticationInfo(user, user.getUserPassword(), this.getClass().getName());
        }
    }

