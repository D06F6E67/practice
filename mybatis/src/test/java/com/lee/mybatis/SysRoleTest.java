package com.lee.mybatis;

import com.lee.mybatis.domain.SysRole;
import com.lee.mybatis.enums.Enabled;
import com.lee.mybatis.mapper.RoleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    public void selectAllRoleAndPrivilege() {
        List<SysRole> sysRoles = roleMapper.selectAllRoleAndPrivilege();
        System.out.println("数量：" + sysRoles.size());
        sysRoles.forEach(sysRole -> {
            System.out.println("角色名：" + sysRole.getRoleName());
            sysRole.getPrivilegeList().forEach(sysPrivilege -> {
                System.out.println("  权限名：" + sysPrivilege.getPrivilegeName());
            });
        });
    }

    @Test
    public void selectRoleAndPrivilegeByUserId() {
        List<SysRole> sysRoles = roleMapper.selectRoleAndPrivilegeByUserId(1L);
        sysRoles.forEach(sysRole -> {
            System.out.println("角色名：" + sysRole.getRoleName());
            sysRole.getPrivilegeList().forEach(sysPrivilege -> {
                System.out.println("  权限名：" + sysPrivilege.getPrivilegeName());
            });
        });
    }

    @Test
    public void selectRoleByUserIdChoose() {
        List<SysRole> sysRoles = roleMapper.selectRoleByUserIdChoose(1L);
        sysRoles.forEach(sysRole -> {
            System.out.println("角色名：" + sysRole.getRoleName());
            if (sysRole.getId().equals(1L)){
                Assert.assertNotNull(sysRole.getPrivilegeList());
            } else if(sysRole.getId().equals(2L)){
                Assert.assertNull(sysRole.getPrivilegeList());
            }
            if (Objects.nonNull(sysRole.getPrivilegeList())) {
                sysRole.getPrivilegeList().forEach(sysPrivilege -> {
                    System.out.println("权限名：" + sysPrivilege.getPrivilegeName());
                });
            }
        });
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
    // @Transactional
    public void inset() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("test");
        sysRole.setEnabled(Enabled.ENABLED);
        sysRole.setCreateBy(1L);
        sysRole.setCreateTime(new Date());
        int insert = roleMapper.insert3(sysRole);
        Assert.assertEquals(1, insert);
        System.out.println(sysRole.getId());
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testCache() {
        SysRole sysRole = roleMapper.selectById(1L);
        sysRole.setRoleName("New Name");
        SysRole sysRole1 = roleMapper.selectById(1L);
        // 虽然没有更新数据库但是这个用户名和sysRole重新赋值的名字相同
        Assert.assertEquals("New Name", sysRole1.getRoleName());
        // 无论如何，sysRole和sysRole1完全是同一个实例
        Assert.assertEquals(sysRole, sysRole1);
    }


}
