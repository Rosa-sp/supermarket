package com.qqw.supermarket.dao;

import com.qqw.supermarket.model.Purchase;

import java.util.List;
import java.util.Map;

public interface PurchaseDao {
    /**
     * 根据id删除  逻辑删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Purchase record);


    /**
     * 根据id查询进货记录
     * @param id
     * @return
     */
    Purchase selectById(Integer id);

    /**
     * 根据id修改
     * @param record
     * @return
     */
    int updateById(Purchase record);

    /**
     * 分页
     * @param map
     * @return
     */
    List<Purchase> listPurchaseByPage(Map<String,Object> map);
}