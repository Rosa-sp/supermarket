package com.qqw.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Role;
import com.qqw.supermarket.model.Sale;
import com.qqw.supermarket.service.SaleService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname SaleController
 * @Description TODO()
 * @Date 2020/2/21 20:53
 * @Author by Alex
 */
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Resource
    private SaleService saleService;

    /**
     * 销售主页面
     * @return
     */
    @RequestMapping()
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("management/admin/sale/index");
        return mv;
    }


    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/pages")
    @ResponseBody
    public PageInfo<Sale> listByPages(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      String sort, String order, String start,String end)throws ParseException{
        Map<String, Object> params=new HashMap();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(start);
        Date date1 = sdf.parse(end);
        params.put("sort",sort);
        params.put("order",order);
        params.put("startDate",date);
        params.put("endDate",date1);
        List<Sale> list = saleService.listSaleByPage(pageNum, pageSize, params);
        PageInfo<Sale> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 跳转到修改页面并获取id
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/sale/create");
        mv.addObject("sale",saleService.selectById(id));
        return mv;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/iframe/edit")
    public ModelAndView edit(){
        ModelAndView mv = new ModelAndView("management/admin/sale/create");
        return mv;
    }

    /**
     * 新增
     * @param sale
     * @return
     */
    @PostMapping("/iframe/save")
    public ResultData save(Sale sale) {
        return saleService.add(sale);
    }

    /**
     * 修改销售记录
     * @param sale
     * @return
     */
    @PostMapping("/iframe/update")
    public ResultData update(Sale sale){
        return saleService.edit(sale);
    }


    /**
     * 删除销售记录
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultData delete(Integer id){
        return saleService.deleteById(id);
    }
}
