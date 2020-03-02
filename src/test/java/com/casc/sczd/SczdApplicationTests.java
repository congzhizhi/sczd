package com.casc.sczd;

import com.casc.sczd.mapper.RoleMapper;
import com.casc.sczd.mapper.SyslogMapper;
import com.casc.sczd.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SczdApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SyslogMapper syslogMapper;
    @Autowired
    RoleMapper roleMapper;
    @Test
    void contextLoads() {
//        mapper.get(2);

//        userMapper.selectById((long) 1);
//        System.out.println(userMapper.selectRolesByUserId((long) 1));
//        SysUser sysUser = new SysUser();
//        sysUser.setUserName("丛治志");
//        sysUser.setUserEmail("congzhizhi@163.com");
//        sysUser.setUserInfo("山东烟台人");
//        sysUser.setCreateTime(new Date());
//        sysUser.setUserPassword("331545523");
//        sysUser.setHeadImg("congzhizhi".getBytes());
//        List<SysUser> list =  new ArrayList<SysUser>();
//        list.add(sysUser);
//        System.out.println(userMapper.insertList(list));

//        System.out.println(syslogMapper.selectById(2l) );

        ;
        System.out.println(userMapper.getRolesAndPrivilegesByUsername("admin"));
        System.out.println();

    }


}
