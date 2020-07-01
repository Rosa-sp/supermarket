package com.qqw.supermarket.dao;

import com.google.gson.Gson;
import com.qqw.supermarket.model.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsDao {
    int deleteByNumber(Integer number);

    int insert(Goods record);

    /**
     * 所有商品
     * @param map
     * @return
     */
    List<Goods> list(Map<String,Object> map);

    Goods selectById(Integer id);

    int update(Goods record);

    /**
     * 查看类别所在的商品数量
     * @param id
     * @return
     */
    int getCategoryGoodsNumber(Integer id);


    Long[] listAllCategory();

    /**
     * 对应类别下的商品
     * @param categoryId
     * @return
     */
    List<Goods> listCategoryGoods(Integer categoryId);
}