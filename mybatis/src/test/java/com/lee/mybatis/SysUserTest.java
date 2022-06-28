package com.lee.mybatis;

import com.lee.mybatis.domain.SysUser;
import com.lee.mybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
class SysUserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        List<SysUser> list = userMapper.selectAll();
        list.forEach(obj -> {
            System.out.println(obj.toString());
        });
    }

    @Test
    public void selectById() {
        // 获取用户id是1的用户
        SysUser sysUser = userMapper.selectById(1L);
        // 用户不为空
        Assert.assertNotNull(sysUser);
        // 用户名是admin
        Assert.assertEquals("admin", sysUser.getUserName());
    }

    @Test
    public void selectUserById() {
        SysUser user = new SysUser();
        user.setId(1L);
        userMapper.selectUserById(user);
        Assert.assertNotNull(user.getUserName());
        System.out.println(user.getUserName());
    }

    @Test
    public void selectUserPage() {
        Map<String, Object> param = new HashMap<>();
        param.put("userName", "ad");
        param.put("offset", 0);
        param.put("limit", 10);
        List<SysUser> userList = userMapper.selectUserPage(param);
        Long total = Long.valueOf(param.get("total").toString());
        System.out.println("数量：" + total);
        userList.forEach(sysUser -> {
            System.out.println("用户名：" + sysUser.getUserName());
        });
    }

    @Test
    public void selectUserAndRoleById() {
        SysUser sysUser = userMapper.selectUserAndRoleById(1001L);
        Assert.assertNotNull(sysUser);
        Assert.assertNotNull(sysUser.getRole());
        System.out.println(sysUser);
    }

    @Test
    public void selectUserAndRoleById2() {
        SysUser sysUser = userMapper.selectUserAndRoleById2(1001L);
        Assert.assertNotNull(sysUser);
        System.out.println("getRole()");
        Assert.assertNotNull(sysUser.getRole());
        System.out.println(sysUser);
    }

    @Test
    public void selectUserRolesAndPrivilegeByUserId() {
        SysUser sysUser = userMapper.selectUserRolesAndPrivilegeByUserId(1L);
        Assert.assertNotNull(sysUser);
        System.out.println("用户名：" + sysUser.getUserName());
        sysUser.getRoleList().forEach(sysRole -> {
            System.out.println("角色名：" + sysRole.getRoleName());
            sysRole.getPrivilegeList().forEach(sysPrivilege -> {
                System.out.println("权限名：" + sysPrivilege.getPrivilegeName());
            });
        });
    }

    @Test
    public void selectAllUserAndRole() {
        List<SysUser> userList = userMapper.selectAllUserAndRole();
        System.out.println("数量：" + userList.size());
        userList.forEach(user -> {
            System.out.println("用户名：" + user.getUserName());
            user.getRoleList().forEach(role -> {
                System.out.println("角色名：" + role.getRoleName());
            });
        });
        /**
         * 测试结果发现查询出来有三条记录，但是数量输出只有两条，也就是说其中两条被合并了
         * 数据：1 admin 123456 管理员 // 2 admin 123456 普通用户 // 1001 test 123456 普通用户
         * 输出：用户：admin 角色：管理员 普通用户 // 用户：test 角色：普通用户
         * mybatis的合并规则是通过<resultMap>中的<id>进行判断，如果<id>中的字段相同就只保留一天数据
         * 为了测试将<id>改为user_password
         * 输出：用户：admin 角色：管理员 普通用户
         * 因为角色<resultMap>中的<id>相同所以普通用户只保留了一条
         * 如果没有配置<id>属性mybatis会把所有字段都比较一次，只要有一个字段值不同，就不合并。
         *
         * 注意：如果<resultMap>中配置了<id>属性，但是没有将<id>属性配置的列查询出来，会导致id对应的都为null最终只会有一条数据。
         *      所以配置了<id>属性，在查询是一定要包含该列。
         *  经过测试和上方(书中)说的不同，配置了<id>属性，查询列中没有<id>属性列，就不会合并。可能是mybatis升级了该地方。
         */
    }

    @Test
    public void selectAllUserAndRoleAndPrivilege() {
        List<SysUser> userList = userMapper.selectAllUserAndRoleAndPrivilege();
        System.out.println("数量：" + userList.size());
        userList.forEach(sysUser -> {
            System.out.println("用户名：" + sysUser.getUserName());
            sysUser.getRoleList().forEach(sysRole -> {
                System.out.println("  角色名：" + sysRole.getRoleName());
                sysRole.getPrivilegeList().forEach(sysPrivilege -> {
                    System.out.println("    权限名：" + sysPrivilege.getPrivilegeName());
                });
            });
        });
    }

    @Test
    public void selectByIdOrUserName() {
        SysUser sysUser = new SysUser();
        SysUser sysUser1 = userMapper.selectByIdOrUserName(sysUser);
        Assert.assertNull(sysUser1);
        sysUser.setUserName("admin");
        SysUser sysUser2 = userMapper.selectByIdOrUserName(sysUser);
        Assert.assertNotNull(sysUser2);
        sysUser.setId(1L);
        SysUser sysUser3 = userMapper.selectByIdOrUserName(sysUser);
        Assert.assertNotNull(sysUser3);
    }

    @Test
    public void selectByIdList() {
        List<Long> longs = Arrays.asList(1L, 1001L);
        List<SysUser> list = userMapper.selectByIdList(longs);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void selectByUser() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("ad");
        List<SysUser> list = userMapper.selectByUser(sysUser);
        Assert.assertTrue(list.size() > 0);

        sysUser = new SysUser();
        sysUser.setUserEmail("test@163.com");
        list = userMapper.selectByUser(sysUser);
        Assert.assertTrue(list.size() > 0);

        sysUser = new SysUser();
        sysUser.setUserName("ad");
        sysUser.setUserEmail("test@163.com");
        list = userMapper.selectByUser(sysUser);
        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void selectAll() {
        // 获取所有用户
        List<SysUser> list = userMapper.selectAll();
        // 不为空
        Assert.assertNotNull(list);
        // 数量大于0
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    // @Transactional
    public void insert() {
        SysUser user = new SysUser();
        user.setUserName("test1");
        user.setUserPassword("123456");
        user.setUserEmail("test@123");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);
        Assert.assertEquals(1, insert);
        Assert.assertNull(user.getId());

    }

    @Test
    // @Transactional
    public void insert2() {
        SysUser user = new SysUser();
        user.setUserName("test1");
        user.setUserPassword("123456");
        // user.setUserEmail("test@123");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(new Date());
        int insert = userMapper.insert2(user);
        Assert.assertEquals(1, insert);
        Assert.assertNotNull(user.getId());
        System.out.println(user.getId());
        userMapper.selectById(user.getId());
    }

    @Test
    // @Transactional
    public void insert3() {
        SysUser user = new SysUser();
        user.setUserName("test1");
        user.setUserPassword("123456");
        user.setUserEmail("test@123");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(new Date());
        int insert = userMapper.insert3(user);
        Assert.assertEquals(1, insert);
        Assert.assertNotNull(user.getId());
        System.out.println(user.getId());
    }

    @Test
    // @Transactional
    public void insertList() {
        List<SysUser> userList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SysUser user = new SysUser();
            user.setUserName("test" + i);
            user.setUserPassword("123456");
            user.setUserEmail("123@123");
            userList.add(user);
        }
        int i = userMapper.insertList(userList);
        Assert.assertEquals(2, i);
        userList.forEach(user -> {
            System.out.println(user.getId());
        });
    }

    @Test
    public void insertUserAndRoles() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test1");
        sysUser.setUserPassword("123456");
        sysUser.setUserEmail("test1@163");
        sysUser.setUserInfo("test1 info");
        sysUser.setHeadImg(new byte[]{1,2});
        // 使用存储过程保存用户信息和角色关联关系
        userMapper.insertUserAndRoles(sysUser, "1,2");
        Assert.assertNotNull(sysUser.getId());
        Assert.assertNotNull(sysUser.getCreateTime());
    }

    @Test
    // @Transactional
    public void updateById() {
        SysUser user = userMapper.selectById(1L);
        Assert.assertEquals("admin", user.getUserName());
        user.setUserName("admin_test");
        int i = userMapper.updateById(user);
        Assert.assertEquals(1, i);
        SysUser user1 = userMapper.selectById(1L);
        Assert.assertEquals("admin_test", user1.getUserName());
    }

    @Test
    // @Transactional
    public void updateByIdSelective() {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUserEmail("test@123");
        int i = userMapper.updateByIdSelective(sysUser);
        Assert.assertEquals(1, i);
        SysUser sysUser1 = userMapper.selectById(1L);
        Assert.assertEquals("test@123", sysUser1.getUserEmail());
    }

    @Test
    // @Transactional
    public void updateByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("user_email", "test@123");
        map.put("user_password", "1234");
        userMapper.updateByMap(map);
        SysUser sysUser = userMapper.selectById(1L);
        Assert.assertEquals("test@123", sysUser.getUserEmail());
    }

    @Test
    // @Transactional
    public void deleteById() {
        SysUser user = userMapper.selectById(1L);
        Assert.assertNotNull(user);
        int i = userMapper.deleteById(1L);
        Assert.assertEquals(1, i);
        SysUser user1 = userMapper.selectById(1L);
        Assert.assertNull(user1);
    }

    @Test
    public void deleteUserById() {
        userMapper.deleteUserById(1016L);
    }

    /**
     * 一级缓存测试
     */
    @Test
    @Transactional
    public void testCache() {
        // 在springboot中只有开启事务才会使用一级缓存，否则每次查询都是一个新的sqlsession一级缓存也就无效
        SysUser sysUser1 = userMapper.selectById(1L);
        sysUser1.setUserName("New Name");
        SysUser sysUser2 = userMapper.selectById(1L);
        Assert.assertEquals("New Name", sysUser2.getUserName());
        Assert.assertEquals(sysUser1, sysUser2);
    }

}
