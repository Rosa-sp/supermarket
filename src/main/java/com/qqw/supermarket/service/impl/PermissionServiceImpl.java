package com.qqw.supermarket.service.impl;


import com.qqw.supermarket.dao.PermissionDao;
import com.qqw.supermarket.model.Permission;
import com.qqw.supermarket.model.Role;
import com.qqw.supermarket.service.PermissionService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Classname PermissionServiceImpl
 * @Description TODO()
 * @Date 2020/2/15 9:17
 * @Author by Alex
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao dao;


    @Override
    public List<Permission> findAllByRoles(List<Role> list) {
        List<Permission> permissions = dao.selectAllPermissionByRoles(list);
        return treeChildren(permissions, 0);
    }

    @Override
    public List<Permission> findAllByPage(Map<String, Object> params) {
        //PageHelper.startPage(pageNum, pageSize);
        return dao.listPermissionByPage(params);
    }

    @Override
    public Permission getById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public ResultData insertPermission(Permission permission) {
        //TODO (检查名称是否重复,is_del = 0)
        int row = dao.insertSelective(permission);
        if (row > 0){
            return new ResultData(1, "添加成功", null);
        }
        return new ResultData(0, "添加失败", null);
    }

    @Override
    public ResultData edit(Permission permission) {
        int rows = dao.updateByPrimaryKeySelective(permission);
        if (rows > 0) {
            return new ResultData(1, "修改成功", null);
        }
        return new ResultData(0, "修改失败", null);
    }

    @Override
    public ResultData treeList() {
        //我们只拿到最顶层节点，parentId = 0
        Permission permission= treeList(dao.listPermissionByPage(null));
        if (StringUtils.isEmpty(permission)){
            return new ResultData(0, "系统异常，请稍后重试", null);
        }
        return new ResultData(1, "success", permission);
    }

    @Override
    public ResultData delete(Integer id) {
        int rows = dao.deleteById(id);
        if (rows > 0) {
            return new ResultData(1, "删除成功", null);
        }
        return new ResultData(0, "删除失败", null);
    }

    /**
     * 获取菜单树
     * @param list
     * @return
     */
    private Permission treeList(List<Permission> list){
        //创建一个顶级节点权限
        Permission top = new Permission();
        top.setId(0);
        top.setName("顶级节点");
        top.setParentId(0);
        top.setChildren(treeChildren(list, 0));
        return top;
    }

    /**
     * 获取子菜单
     * @param list
     * @param pid
     * @return
     */
    public List<Permission> treeChildren(List<Permission> list, int pid){
        List<Permission> children = new ArrayList<>();
        Iterator<Permission> iterator = list.iterator();
        while (iterator.hasNext()){
            Permission permission = iterator.next();
            //如果已经处理过就不处理此节点
            if(permission.isCooked()){
                continue;
            }
            if (permission.getParentId() == pid){
                permission.setChildren(treeChildren(list, permission.getId()));
                children.add(permission);
                permission.setCooked(true);
            }
        }
        return children.size() > 0 ? children : null;
    }
}
