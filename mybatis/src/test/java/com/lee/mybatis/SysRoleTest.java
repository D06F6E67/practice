package com.lee.mybatis;

import com.lee.mybatis.domain.SysRole;
import com.lee.mybatis.mapper.RoleMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SysRoleTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void selectRoleByUserId() {
        List<SysRole> sysRoles = roleMapper.selectRoleByUserId(1L);

        Assert.assertNotNull(sysRoles);
        Assert.assertTrue(sysRoles.size() > 0);
    }

    @Test
    public void selectRoleByUserIdAndEnabled() {
        List<SysRole> sysRoles = roleMapper.selectRoleByUserIdAndEnabled(1L, 1);
        Assert.assertNotNull(sysRoles);
        Assert.assertTrue(sysRoles.size() > 0);
    }

    @Test
    public void selectById() {
        SysRole sysRole = roleMapper.selectById2(1L);
        System.out.println(sysRole.toString());
    }

    @Test
    public void selectAll() {
        List<SysRole> sysRoles = roleMapper.selectAll();
        sysRoles.forEach(sysRole -> {
            System.out.println(sysRole.toString());
        });
    }

    @Test
    @Transactional
    public void inset() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("test");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(1L);
        sysRole.setCreateTime(new Date());
        int insert = roleMapper.insert3(sysRole);
        Assert.assertEquals(1, insert);
        System.out.println(sysRole.getId());
    }


}
