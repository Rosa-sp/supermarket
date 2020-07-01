package com.qqw.supermarket.service;

import com.qqw.supermarket.model.Sale;
import com.qqw.supermarket.util.ResultData;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname SaleService
 * @Description TODO()
 * @Date 2020/2/21 20:46
 * @Author by Alex
 */
public interface SaleService {
    /**
     * 根据id删除
     * @param id
     * @return
     */
    ResultData deleteById(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    ResultData add(Sale record);

    /**
     * 查看
     * @param id
     * @return
     */
    Sale selectById(Integer id);


    /**
     * 修改
     * @param record
     * @return
     */
    ResultData edit(Sale record);

    /**
     * 分页，查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    List<Sale> listSaleByPage(int pageNum, int pageSize, Map<String, Object> params);

    /**
     * 查询有多少条数据
     * @return
     */
    int count();
}
