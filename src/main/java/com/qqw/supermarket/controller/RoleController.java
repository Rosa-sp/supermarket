package com.qqw.supermarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Permission;
import com.qqw.supermarket.model.Role;
import com.qqw.supermarket.service.PermissionService;
import com.qqw.supermarket.service.RoleService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname RoleController
 * @Description TODO()
 * @Date 2020/2/15 17:21
 * @Author by Alex
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService service;

    @Resource
    private PermissionService permissionService;

    /**
     * 角色主页
     * @return
     */
    @RequestMapping()
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("management/admin/systemRoles/index");
        return mv;
    }

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @param search
     * @return
     */
    @GetMapping("/pages")
    @ResponseBody
    public PageInfo<Role> listByPages(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "5") int pageSize,
                                      String sort, String order, String search){
        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        params.put("order", order);
        params.put("search", search);
        List<Role> list = service.findAllByPage(pageNum, pageSize, params);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 获取id，跳转
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/systemRoles/create");
        mv.addObject("role",service.getById(id));
        return mv;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/iframe/edit")
    public ModelAndView edit(){
        ModelAndView mv = new ModelAndView("management/admin/systemRoles/create");
        return mv;
    }

    /**
     * 新增
     * @param role
     * @return
     */
    @PostMapping("/iframe/save")
    public ResultData save(Role role) {
        return service.insertRole(role);
    }

    /***
     * 修改
     * @param role
     * @return
     */
    @PostMapping("/iframe/update")
    public ResultData update(Role role){
        return service.update(role);
    }

    /**
     * 详情信息
     * @param id
     * @return
     */
    @GetMapping("/iframe/info/{id}")
    public ModelAndView info(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/systemRoles/info");
        mv.addObject("role",service.getById(id));
        return mv;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultData delete(Integer id){
       return  service.deleteById(id);
    }

    /**
     * 批量删除
     * @param accountList
     * @return
     */
    @PostMapping("/batchDelete")
    public ResultData batchDelete(Integer[] accountList){
        //String[] arr = roleList.split(",");
        return  service.batchDelete(accountList);
    }


    /**
     * 授权
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/iframe/edit/role/{id}")
    public ModelAndView editRole(@PathVariable("id") Integer id) throws JsonProcessingException {
        ModelAndView mv = new ModelAndView("management/admin/systemRoles/role");
        mv.addObject("role", service.getById(id));
        ObjectMapper mapper = new ObjectMapper();
        //所有权限树
        Permission permission = (Permission) permissionService.treeList().getData();
        mv.addObject("permission",mapper.writeValueAsString(permission.getChildren()));
        return mv;
    }

    @PostMapping("/iframe/role/edit")
    public ResultData editRole(Integer id, Integer[] ids) {
        return service.editRole(id, ids);
    }
}
