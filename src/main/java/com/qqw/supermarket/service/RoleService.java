package com.qqw.supermarket.service;


import com.qqw.supermarket.model.Role;
import com.qqw.supermarket.util.ResultData;

import java.util.List;
import java.util.Map;

/**
 * @Classname RoleService
 * @Description TODO()
 * @Date 2020/2/13 19:54
 * @Author by Alex
 */

public interface RoleService {
    /**
     * 查询全部角色
     * @return
     */
    List<Role> findAll();

    /**
     * 角色分页，查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    List<Role> findAllByPage(int pageNum, int pageSize, Map<String, Object> params);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role getById(Integer id);

    /**
     * 添加角色
     * @param role
     * @return
     */
    ResultData insertRole(Role role);

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    ResultData deleteById(Integer id);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    ResultData batchDelete(Integer[] ids);

    /**
     * 角色更新
     * @param role
     * @return
     */
    ResultData update(Role role);

    /**
     * 角色授权
     * @param id
     * @param ids
     * @return
     */
    ResultData editRole(Integer id, Integer[] ids);
}
