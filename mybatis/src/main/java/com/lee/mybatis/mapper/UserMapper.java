package com.lee.mybatis.mapper;

import com.lee.mybatis.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

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
     * 根据用户id或用户名查询
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户id集合查询
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);

    /**
     * 根据动态条件查询用户信息
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
     * 新增用户－使用 useGeneratedKeys 方式
     * @param sysUser
     * @return 用户id
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户－使用 selectKey 方式
     * @param sysUser
     * @return 用户id
     */
    int insert3(SysUser sysUser);

    /**
     * 批量插入用户
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 通过Map更新列
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

}
