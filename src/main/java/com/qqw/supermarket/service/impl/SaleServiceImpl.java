package com.qqw.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.qqw.supermarket.dao.SaleDao;
import com.qqw.supermarket.model.Sale;
import com.qqw.supermarket.service.SaleService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname SaleServiceImpl
 * @Description TODO()
 * @Date 2020/2/21 20:48
 * @Author by Alex
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleDao saleDao;

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResultData deleteById(Integer id) {
        int row = saleDao.deleteById(id);
        if (row > 0) {
            return new ResultData(1, "删除成功",null);
        }
        return new ResultData(0,"删除失败",null);
    }

    /**
     * 新增
     * @param record
     * @return
     */
    @Override
    public ResultData add(Sale record) {
        int row = saleDao.insert(record);
        if (row > 0) {
            return new ResultData(1, "添加成功",null);
        }
        return new ResultData(0, "添加失败",null);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public Sale selectById(Integer id) {
        return saleDao.selectById(id);
    }



    /**
     * 修改
     * @param record
     * @return
     */
    @Override
    public ResultData edit(Sale record) {
        int row = saleDao.updateById(record);
        if (row > 0) {
            return new ResultData(1, "修改成功",null);
        }
        return new ResultData(0, "修改失败",null);
    }


    /**
     * 分页
      * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    @Override
    public List<Sale> listSaleByPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        return saleDao.selectAll(params);
    }

    /**
     * 总数量
     * @return
     */
    @Override
    public int count() {
        return saleDao.count();
    }
}
