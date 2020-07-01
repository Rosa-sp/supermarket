package com.qqw.supermarket.service;

import com.qqw.supermarket.model.Permission;
import com.qqw.supermarket.model.Role;
import com.qqw.supermarket.util.ResultData;

import java.util.List;
import java.util.Map;

/**
 * @Classname PermissionService
 * @Description TODO()
 * @Date 2020/2/15 9:17
 * @Author by Alex
 */
public interface PermissionService {

    /**
     * 根据角色查询全部的权限
     * @param list
     * @return
     */
    List<Permission> findAllByRoles(List<Role> list);

    /**
     * 分页，查询
     * @param params
     * @return
     */
    List<Permission> findAllByPage(Map<String, Object> params);

    /**
     * 根据id获取权限
     * @param id
     * @return
     */
    Permission getById(Integer id);

    /**
     * 添加权限
     * @param role
     * @return
     */
    ResultData insertPermission(Permission role);

    /**
     * 修改权限
     * @param permission
     * @return
     */
    ResultData edit(Permission permission);

    /**
     * 获取菜单树
     * @return
     */
    ResultData treeList();

    /**
     * 删除权限
     * @param id
     * @return
     */
    ResultData delete(Integer id);

}
