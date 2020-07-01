package com.qqw.supermarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Categorys;
import com.qqw.supermarket.model.Goods;
import com.qqw.supermarket.model.Supplier;
import com.qqw.supermarket.service.GoodsService;
import com.qqw.supermarket.util.ResultData;
import com.qqw.supermarket.util.Tree;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname GoodsController
 * @Description TODO()
 * @Date 2020/2/16 17:45
 * @Author by Alex
 */
@RestController
@RequestMapping("/offineGoods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;


    @RequestMapping
    public ModelAndView index() throws JsonProcessingException {
        ModelAndView mv = new ModelAndView("management/admin/goods/index");

        ObjectMapper mapper = new ObjectMapper();
        //所有权限树
        Categorys categorys = (Categorys) goodsService.treeList().getData();
        mv.addObject("categorys",mapper.writeValueAsString(categorys.getChildren()));

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
    public PageInfo<Goods> listByPages(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize,
                                       String sort, String order, String search,Integer id){
        Map<String, Object> params = new HashMap<>();
        params.put("sort",sort);
        params.put("order",order);
        params.put("search",search);
        params.put("categoryId",id);
        List<Goods> list = goodsService.listGoodsByPage(pageNum, pageSize, params);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }



    /**
     * 按商品分类处理
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
//    @PostMapping("/category")
//    public PageInfo<Goods> listCategoryGoods(@RequestParam(defaultValue = "1") int pageNum,
//                                             @RequestParam(defaultValue = "10") int pageSize,
//                                             Integer id){
//        Integer categoryId = id;
//        List<Goods> list = goodsService.listCategoryGoods(pageNum, pageSize, categoryId);
//        PageInfo<Goods> pageInfo = new PageInfo<>(list);
//        return pageInfo;
//    }

    /**
     * 树形菜单
     */
     /*@GetMapping("/tree")
    public Tree<Categorys> tree() {
        Tree<Categorys> tree = new Tree<Categorys>();
        tree = goodsService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    public ModelAndView treeView(){
        ModelAndView mv = new ModelAndView("management/admin/goods/goodsTree");
        return mv;
    }*/


    /**
     * 详情信息
     * @param id
     * @return
     */
    @GetMapping("/iframe/info/{id}")
    public ModelAndView info(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/goods/info");
        mv.addObject("goods",goodsService.selectById(id));
        return mv;
    }

    /**
     * 跳转到修改页面并获取id
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("management/admin/goods/create");
        mv.addObject("goods",goodsService.selectById(id));
        return mv;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/iframe/edit")
    public ModelAndView edit(){
        ModelAndView mv = new ModelAndView("management/admin/goods/create");
        return mv;
    }

    /**
     * 新增
     * @param goods
     * @return
     */
    @PostMapping("/iframe/save")
    public ResultData save(Goods goods) {
        return goodsService.add(goods);
    }

    /**
     * 修改
     * @param goods
     * @return
     */
    @PostMapping("/iframe/edit")
    public ResultData update(Goods goods) {
        return goodsService.edit(goods);
    }

    /**
     * 删除
     * @param number
     * @return
     */
    @PostMapping("/delete")
    public ResultData delete(Integer number){
        return  goodsService.deleteByNumber(number);
    }
}
