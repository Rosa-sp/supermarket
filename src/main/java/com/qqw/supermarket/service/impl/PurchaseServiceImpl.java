package com.qqw.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.qqw.supermarket.dao.PurchaseDao;
import com.qqw.supermarket.model.Purchase;
import com.qqw.supermarket.service.PurchaseService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Classname PurchaseServiceImpl
 * @Description TODO()
 * @Date 2020/2/22 17:09
 * @Author by Alex
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseDao purchaseDao;

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public ResultData deleteById(Integer id) {
        int row = purchaseDao.deleteById(id);
        if (row > 0) {
            return new ResultData(1, "删除成功",null);
        }
        return new ResultData(0, "删除失败",null);
    }

    /**
     * 新增
     * @param record
     * @return
     */
    @Override
    public ResultData add(Purchase record) {
        int row = purchaseDao.insert(record);
        if (row > 0) {
            return new ResultData(1, "添加成功",null);
        }
        return new ResultData(0, "添加失败",null);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Purchase selectById(Integer id) {

        return  purchaseDao.selectById(id);

    }

    /**
     * 修改信息
     * @param record
     * @return
     */
    @Override
    public ResultData edit(Purchase record) {
        int row = purchaseDao.updateById(record);
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
    public List<Purchase> listPurchaseByPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        return purchaseDao.listPurchaseByPage(params);
    }
}
