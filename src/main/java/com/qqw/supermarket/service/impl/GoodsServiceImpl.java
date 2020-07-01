package com.qqw.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.qqw.supermarket.dao.CategorysDao;
import com.qqw.supermarket.dao.GoodsDao;
import com.qqw.supermarket.model.Categorys;
import com.qqw.supermarket.model.Goods;
import com.qqw.supermarket.service.GoodsService;
import com.qqw.supermarket.util.BuildTree;
import com.qqw.supermarket.util.ResultData;
import com.qqw.supermarket.util.Tree;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Classname GoodsServiceImpl
 * @Description TODO()
 * @Date 2020/4/20 13:42
 * @Author by Alex
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private CategorysDao categorysDao;

    /**
     * 删除
     * @param number
     * @return
     */
    @Override
    public ResultData deleteByNumber(Integer number) {
        int row = goodsDao.deleteByNumber(number);
        if (row > 0) {
            return new ResultData(1, "删除成功",null);
        }
        return new ResultData(0,"删除失败",null);
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @Override
    public ResultData add(Goods record) {
        int row = goodsDao.insert(record);
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
    public Goods selectById(Integer id) {
        return goodsDao.selectById(id);
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @Override
    public ResultData edit(Goods record) {
        int row = goodsDao.update(record);
        if (row > 0) {
            return new ResultData(1, "修改成功",null);
        }
        return new ResultData(0, "修改失败",null);
    }



    /**
     *
     * 树形菜单
     * @return
     */
/*
    @Override
    public Tree<Categorys> getTree() {
        List<Tree<Categorys>> trees = new ArrayList<Tree<Categorys>>();
        List<Categorys> categorys = categorysDao.listCategorys(new HashMap<String, Object>(16));
        Long[] pCategorys = categorysDao.listParentCategorys();
        Long[] uCategorys = goodsDao.listAllCategory();
        Long[] allCategorys = (Long[]) ArrayUtils.addAll(pCategorys, uCategorys);
        for (Categorys category : categorys) {
            if (!ArrayUtils.contains(allCategorys, category.getId())) {
                continue;
            }
            Tree<Categorys> tree = new Tree<Categorys>();
            tree.setId(category.getId().toString());
            tree.setParentId(category.getParentId().toString());
            tree.setText(category.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "category");
            tree.setState(state);
            trees.add(tree);
        }
        List<Goods> goods = goodsDao.list(new HashMap<String, Object>(16));
        for (Goods good : goods) {
            Tree<Categorys> tree = new Tree<Categorys>();
            tree.setId(good.getId().toString());
            tree.setParentId(good.getCategoryId().toString());
            tree.setText(good.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Categorys> t = BuildTree.build(trees);
        return t;
    }*/


    @Override
    public List<Goods> listGoodsByPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        return goodsDao.list(params);
    }

    /**
     * 对应类别下的商品
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public List<Goods> listCategoryGoods(int pageNum, int pageSize, Integer categoryId) {
        PageHelper.startPage(pageNum,pageSize);
        return goodsDao.listCategoryGoods(categoryId);
    }


    @Override
    public ResultData treeList() {
        //我们只拿到最顶层节点，parentId = 0
        Categorys categorys= treeList(categorysDao.listCategorys(null));
        if (StringUtils.isEmpty(categorys)){
            return new ResultData(0, "系统异常，请稍后重试", null);
        }
        return new ResultData(1, "success", categorys);
    }

    /**
     * 获取菜单树
     * @param list
     * @return
     */
    private Categorys treeList(List<Categorys> list){
        //创建一个顶级节点权限
        Categorys top = new Categorys();
        top.setId(0);
        top.setName("顶级节点");
        top.setParentId(0);
        top.setChildren(treeChildren(list, 0));
        return top;
    }

    /**
     * 获取子菜单
     * @param list
     * @param pid
     * @return
     */
    public List<Categorys> treeChildren(List<Categorys> list, int pid){
        List<Categorys > children = new ArrayList<>();
        Iterator<Categorys> iterator = list.iterator();
        while (iterator.hasNext()){
            Categorys categorys = iterator.next();
            //如果已经处理过就不处理此节点
            if(categorys.isCooked()){
                continue;
            }
            if (categorys.getParentId() == pid){
                categorys.setChildren(treeChildren(list, categorys.getId()));
                children.add(categorys);
                categorys.setCooked(true);
            }
        }
        return children.size() > 0 ? children : null;
    }

}
