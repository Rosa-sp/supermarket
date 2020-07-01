package com.qqw.supermarket.dao;

import com.qqw.supermarket.model.Permission;
import com.qqw.supermarket.model.Role;

import java.util.List;
import java.util.Map;

/**
 * @Classname PermissionDao
 * @Description TODO()
 * @Date 2020/1/3 9:08
 * @Author by Alex
 */
public interface PermissionDao {

    /**
     * 根据id删除权限
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加权限
     * @param record
     * @return
     */
    int insertSelective(Permission record);

    /**
     * 根据id查询权限
     * @param id
     * @return
     */
    Permission selectByPrimaryKey(Integer id);

    /**
     * 根据父id查询
     * @param id
     * @return
     */
    List<Permission> selectByTop(Integer id);

    List<Permission> selectByParentId(Integer id);

    /**
     * 根据权限id修改权限
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * 分页
     * @param params
     * @return
     */
    List<Permission> listPermissionByPage(Map<String, Object> params);


    /**
     * 根据id删除角色权限
     * @param id
     * @return
     */
    int deleteRolePermission(Integer id);

    int deleteById(Integer id);

    /**
     * 插入角色权限
     * @param map
     * @return
     */
    int insertRolePermission(Map<String, Object> map);

    /**
     * 根据角色查询全部的权限
     * @param list
     * @return
     */
    List<Permission> selectAllPermissionByRoles(List<Role> list);
}
