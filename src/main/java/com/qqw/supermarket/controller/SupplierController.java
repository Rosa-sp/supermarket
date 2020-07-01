package com.qqw.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Purchase;
import com.qqw.supermarket.model.Sale;
import com.qqw.supermarket.model.Supplier;
import com.qqw.supermarket.service.PurchaseService;
import com.qqw.supermarket.service.SupplierService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SupplierController
 * @Description TODO()
 * @Date 2020/2/22 17:40
 * @Author by Alex
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierService supplierService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("management/admin/supplier/index");
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
    public PageInfo<Supplier> listByPages(@RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "5") int pageSize,
                                          String sort, String order, String search){
        Map<String, Object> params = new HashMap<>();
        params.put("sort",sort);
        params.put("order",order);
        params.put("search",search);
        List<Supplier> list = supplierService.listSupplierByPage(pageNum, pageSize, params);
        PageInfo<Supplier> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 跳转到修改页面并获取id
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/supplier/create");
        mv.addObject("supplier",supplierService.selectById(id));
        return mv;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/iframe/edit")
    public ModelAndView edit(){
        ModelAndView mv = new ModelAndView("management/admin/supplier/create");
        return mv;
    }

    /**
     * 新增
     * @param supplier
     * @return
     */
    @PostMapping("/iframe/save")
    public ResultData save(Supplier supplier) {
        return supplierService.add(supplier);
    }

    /**
     * 修改供货商信息
     * @param supplier
     * @return
     */
    @PostMapping("/iframe/update")
    public ResultData update(Supplier supplier){
        return supplierService.edit(supplier);
    }

    /**
     * 详细信息
     * @param id
     * @return
     */
    @GetMapping("/iframe/info/{id}")
    public ModelAndView info(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/supplier/info");
        mv.addObject("supplier",supplierService.selectById(id));
        return mv;
    }

}
