package com.qqw.supermarket.service;

import com.qqw.supermarket.model.Supplier;
import com.qqw.supermarket.util.ResultData;

import java.util.List;
import java.util.Map;

/**
 * @Classname SupplierService
 * @Description TODO()
 * @Date 2020/2/22 17:34
 * @Author by Alex
 */
public interface SupplierService {
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
    ResultData add(Supplier record);

    /**
     * 查看
     * @param id
     * @return
     */
    Supplier selectById(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    ResultData edit(Supplier record);

    /**
     * 分页，查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    List<Supplier> listSupplierByPage(int pageNum, int pageSize, Map<String, Object> params);
}
