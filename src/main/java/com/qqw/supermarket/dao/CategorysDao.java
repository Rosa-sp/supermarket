package com.qqw.supermarket.dao;

import com.qqw.supermarket.model.Categorys;

import java.util.List;
import java.util.Map;

public interface CategorysDao {
    int deleteById(Integer id);

    int insert(Categorys record);

    Categorys selectById(Integer id);

    int updateById(Categorys record);

    /**
     * 根据父id查询
     * @param id
     * @return
     */
    List<Categorys> selectByTop(Integer id);

    List<Categorys> selectByParentId(Integer id);


    /**
     * 商品父类数组
     * @return
     */
    Long[] listParentCategorys();

    List<Categorys> listCategorys(Map<String,Object> map);
}