package com.qqw.supermarket.service;

import com.qqw.supermarket.model.Categorys;
import com.qqw.supermarket.model.Goods;
import com.qqw.supermarket.util.ResultData;
import com.qqw.supermarket.util.Tree;

import java.util.List;
import java.util.Map;

/**
 * @Classname GoodsService
 * @Description TODO()
 * @Date 2020/4/20 13:38
 * @Author by Alex
 */
public interface GoodsService {
    /**
     * number
     * @param number
     * @return
     */
    ResultData deleteByNumber(Integer number);

    /**
     * 新增
     * @param record
     * @return
     */
    ResultData add(Goods record);

    /**
     * 查看
     * @param id
     * @return
     */
    Goods selectById(Integer id);
    /**
     * 修改
     * @param record
     * @return
     */
    ResultData edit(Goods record);


//    Tree<Categorys> getTree();
    /**
     * 获取菜单树
     * @return
     */
    ResultData treeList();

    /**
     * 分页，查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    List<Goods> listGoodsByPage(int pageNum, int pageSize, Map<String, Object> params);

    /**
     * 对应类别下的商品
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    List<Goods> listCategoryGoods(int pageNum, int pageSize, Integer categoryId);
}
