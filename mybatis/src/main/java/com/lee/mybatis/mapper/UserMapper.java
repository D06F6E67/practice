package com.lee.mybatis.mapper;

import com.lee.mybatis.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 */
@Mapper
public interface UserMapper {

    /**
     * 通过id查询用户
     * @param id 用户id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查询用户信息(使用存储过程)
     * @param user
     */
    void selectUserById(SysUser user);

    /**
     * 分页查询(使用存储过程)
     * @param param userName 用户名
     *              offset 开始位置
     *              limit 数量
     * @return
     */
    List<SysUser> selectUserPage(Map<String, Object> param);

    /**
     * 根据id获取用户和角色信息(一对一映射 使用属性名映射)
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * 根据id获取用户和角色信息(一对一映射 使用association: select映射Bean信息)
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById2(Long id);

    /**
     * 根据用户id获取角色和权限信息(一对多对多 使用resultMap collection)
     * @param userId
     * @return
     */
    SysUser selectUserRolesAndPrivilegeByUserId(Long userId);

    /**
     * 获取所有用户以及对应角色信息(一对对多 使用collection: resultMap映射Bean信息)
     * @return
     */
    List<SysUser> selectAllUserAndRole();

    /**
     * 获取所有用户以及对应角色以及权限信息(一对对多对多 使用collection: resultMap映射Bean信息)
     * @return
     */
    List<SysUser> selectAllUserAndRoleAndPrivilege();

    /**
     * 根据用户id或用户名查询(使用 choose when拼接SQL)
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户id集合查询(使用 foreach拼接SQL)
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);

    /**
     * 根据动态条件查询用户信息(使用 where-if-bind拼接SQL)
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 查询全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 新增用户
     * @param sysUser
     * @return 影响的行数
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户(使用 insert: useGeneratedKeys 返回id)
     * @param sysUser
     * @return 用户id
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户(使用 selectKey 返回id)
     * @param sysUser
     * @return 用户id
     */
    int insert3(SysUser sysUser);

    /**
     * 批量插入用户(使用 foreach拼接SQL)
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * 保存用户信息和角色信息(使用存储过程)
     * @param user 用户信息
     * @param roleIds 角色id
     * @return
     */
    int insertUserAndRoles(@Param("user") SysUser user, @Param("roleIds") String roleIds);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 根据主键更新(使用 set if拼接SQL)
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 通过Map更新列(使用 foreach拼接SQL)
     * @param map
     * @return
     */
    int updateByMap(Map<String, Object> map);

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id删除用户和用户角色信息(使用存储过程)
     * @param id
     * @return
     */
    int deleteUserById(Long id);

}
