package com.qqw.supermarket.service;

import com.qqw.supermarket.model.Purchase;
import com.qqw.supermarket.util.ResultData;

import java.util.List;
import java.util.Map;

/**
 * @Classname PurchaseService
 * @Description TODO()
 * @Date 2020/2/22 17:08
 * @Author by Alex
 */
public interface PurchaseService {
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
    ResultData add(Purchase record);

    /**
     * 查看
     * @param id
     * @return
     */
    Purchase selectById(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    ResultData edit(Purchase record);

    /**
     * 分页，查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    List<Purchase> listPurchaseByPage(int pageNum, int pageSize, Map<String, Object> params);
}
