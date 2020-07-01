package com.qqw.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.qqw.supermarket.dao.AccountDao;
import com.qqw.supermarket.dao.PermissionDao;
import com.qqw.supermarket.dao.RoleDao;
import com.qqw.supermarket.model.Role;
import com.qqw.supermarket.service.RoleService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname RoleServiceImpl
 * @Description TODO()
 * @Date 2020/2/13 19:55
 * @Author by Alex
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private AccountDao accountDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public List<Role> findAll() {
        return roleDao.listRoleByPage(null);
    }

    /**
     * 分页。
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    @Override
    public List<Role> findAllByPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        return roleDao.listRoleByPage(params);
    }

    /**
     * 通过角色id获取角色
     * @param id
     * @return
     */
    @Override
    public Role getById(Integer id) {
        return roleDao.selectById(id);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public ResultData insertRole(Role role) {

        int row  = roleDao.insertSelective(role);
        if (row > 0){
            return new ResultData(1,"添加成功",null);
        }
        return new ResultData(1,"添加失败",null);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public ResultData deleteById(Integer id) {
        int row = accountDao.selectByRId(id).size();
        if (row == 0) {
            roleDao.deleteById(id);
            return new ResultData(1,"删除成功",null);
        }
        return new ResultData(0,"删除失败,此角色仍在使用",null);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public ResultData batchDelete(Integer[] ids) {
        List list = new ArrayList();
        for (int i = 0 ; i < ids.length ; i++){
            if (accountDao.selectByRId(ids[i]) != null) {
                return new ResultData(0,"删除失败,此角色仍在使用",null);
            }
            list.add(ids[i]);
        }
        roleDao.batchDelete(list);
        return new ResultData(1,"删除成功",null);
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    public ResultData update(Role role) {
        int rows =  roleDao.updateByPrimaryKeySelective(role);
        if (rows > 0){
            return  new ResultData(1,"修改角色成功",null);
        }else {
            return  new ResultData(0,"修改失败",null);
        }

    }

    /**
     * 给角色授权
     * @param id
     * @param ids
     * @return
     */
    @Override
    public ResultData editRole(Integer id, Integer[] ids) {
        //删除此角色已有的权限
        permissionDao.deleteRolePermission(id);
        if (ids == null || ids.length == 0){
            return new ResultData( 1,"修改授权成功", null);
        }
        Map<String,Object> map = new HashMap<>();
        //账号id
        map.put("id", id);
        //权限的id数组
        map.put("ids", ids);
        return permissionDao.insertRolePermission(map) > 0 ?
                new ResultData( 1,"授权成功", null)
                : new ResultData( 0,"授权失败，请重试", null);
    }
}
