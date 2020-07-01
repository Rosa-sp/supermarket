package com.qqw.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Permission;
import com.qqw.supermarket.service.PermissionService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname PermissionController
 * @Description TODO()
 * @Date 2020/2/15 9:36
 * @Author by Alex
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService service;

    /**
     * 权限主页
     * @return
     */
    @RequestMapping()
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("management/admin/systemPermission/index");
        return mv;
    }

    /**
     * 分页
     * @param search
     * @return
     */
    @GetMapping("/pages")
    @ResponseBody
    public PageInfo<Permission> listByPages(String search){
        Map<String, Object> params = new HashMap<>();
        params.put("search", search);
        List<Permission> list = service.findAllByPage(params);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 获取权限id并跳转
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/systemPermission/create");
        mv.addObject("permission", service.getById(id));
        return mv;
    }

    /**
     * 跳转到新增页面
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/iframe/edit")
    public ModelAndView permission(Integer id, String name){
        ModelAndView mv = new ModelAndView("management/admin/systemPermission/create");
        mv.addObject("pId", id);
        mv.addObject("name", name);
        return mv;
    }

    /**
     * 修改
     * @param permission
     * @return
     */
    @PostMapping("/iframe/edit")
    public ResultData edit(Permission permission) {
        return service.edit(permission);
    }

    /**
     * 新增
     * @param permission
     * @return
     */
    @PostMapping("/iframe/add")
    public ResultData update(Permission permission){
        return service.insertPermission(permission);
    }

    /**
     * 树节点
     * @return
     */
    @GetMapping("/list")
    public Object listByPages(){
        return service.treeList().getData();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultData del(Integer id){
        return service.delete(id);
    }
}
