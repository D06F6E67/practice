package com.lee.mybatis;

import com.lee.mybatis.domain.SysUser;
import com.lee.mybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    public void selectUserAndRoleById() {
        SysUser sysUser = userMapper.selectUserAndRoleById(1001L);
        Assert.assertNotNull(sysUser);
        Assert.assertNotNull(sysUser.getRole());
        System.out.println(sysUser.toString());
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
    public void deleteById() {
        SysUser user = userMapper.selectById(1L);
        Assert.assertNotNull(user);
        int i = userMapper.deleteById(1L);
        Assert.assertEquals(1, i);
        SysUser user1 = userMapper.selectById(1L);
        Assert.assertNull(user1);
    }

}
