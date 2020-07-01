package com.qqw.supermarket.dao;


import com.qqw.supermarket.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加角色
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Integer id);

    Role selectById(Integer id);

    /**
     * 修改角色
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**’
     * 分页
     * @param params
     * @return
     */
    List<Role> listRoleByPage(Map<String, Object> params);

    int deleteAccountRole(String id);

    int insertAccountRole(Map<String, Object> map);

    /**
     * 批量删除
     * @param list
     * @return
     */
    int batchDelete(List list);
}