package com.qqw.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Purchase;
import com.qqw.supermarket.model.Supplier;
import com.qqw.supermarket.service.PurchaseService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname PurchaseController
 * @Description TODO()
 * @Date 2020/2/17 16:53
 * @Author by Alex
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("management/admin/purchase/index");
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
    public PageInfo<Purchase> listByPages(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "5") int pageSize,
                                       String sort, String order, String search){
        Map<String, Object> params = new HashMap<>();
        params.put("sort",sort);
        params.put("order",order);
        params.put("search",search);
        List<Purchase> list = purchaseService.listPurchaseByPage(pageNum, pageSize, params);
        PageInfo<Purchase> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 跳转到修改页面并获取id
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/purchase/create");
        mv.addObject("purchase",purchaseService.selectById(id));
        return mv;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/iframe/edit")
    public ModelAndView edit(){
        ModelAndView mv = new ModelAndView("management/admin/purchase/create");
        return mv;
    }

    /**
     * 新增
     * @param purchase
     * @return
     */
    @PostMapping("/iframe/save")
    public ResultData save(Purchase purchase) {
        return purchaseService.add(purchase);
    }

    /**
     * 修改供货商信息
     * @param purchase
     * @return
     */
    @PostMapping("/iframe/update")
    public ResultData update(Purchase purchase){
        return purchaseService.edit(purchase);
    }


}
