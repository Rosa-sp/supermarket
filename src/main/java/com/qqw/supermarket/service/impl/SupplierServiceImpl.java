package com.qqw.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.qqw.supermarket.dao.SupplierDao;
import com.qqw.supermarket.model.Supplier;
import com.qqw.supermarket.service.SupplierService;
import com.qqw.supermarket.util.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Classname SupplierServiceImpl
 * @Description TODO()
 * @Date 2020/2/22 17:35
 * @Author by Alex
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierDao supplierDao;


    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResultData deleteById(Integer id) {
        int row = supplierDao.deleteById(id);
        if (row > 0) {
            return new ResultData(1, "删除成功",null);
        }
        return new ResultData(0, "刪除失败",null);
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @Override
    public ResultData add(Supplier record) {
        int row = supplierDao.insert(record);
        if (row >0) {
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
    public Supplier selectById(Integer id) {
        return supplierDao.selectById(id);
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @Override
    public ResultData edit(Supplier record) {
        int row = supplierDao.updateById(record);

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
    public List<Supplier> listSupplierByPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        return supplierDao.listSupplierByPage(params);
    }
}
