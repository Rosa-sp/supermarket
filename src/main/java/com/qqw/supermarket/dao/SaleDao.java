package com.qqw.supermarket.dao;

import com.qqw.supermarket.model.Sale;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SaleDao {
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
    int insert(Sale record);


    /**
     * 根据id查询销售记录
     * @param id
     * @return
     */
    Sale selectById(Integer id);

    /**
     * 查询全部
     * @param params
     * @return
     */
    List<Sale> selectAll(Map<String, Object> params);

    /**
     * 根据id修改
     * @param record
     * @return
     */
    int updateById(Sale record);

    /**
     * 分页
     * @param map
     * @return
     */
    List<Sale> listSaleByPage(Map<String,Object> map);

    /**
     * 查询有多少条数据
     * @return
     */
    int count();
}