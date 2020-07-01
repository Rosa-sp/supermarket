package com.qqw.supermarket.dao;

import com.qqw.supermarket.model.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierDao {
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
    int insert(Supplier record);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    Supplier selectById(Integer id);

    /**
     * 根据id修改
     * @param record
     * @return
     */
    int updateById(Supplier record);

    /**
     * 分页
     * @param map
     * @return
     */
    List<Supplier> listSupplierByPage(Map<String,Object> map);
}