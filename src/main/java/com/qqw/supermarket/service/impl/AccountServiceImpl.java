package com.qqw.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.qqw.supermarket.dao.AccountDao;
import com.qqw.supermarket.dao.RoleDao;
import com.qqw.supermarket.model.Account;
import com.qqw.supermarket.model.AccountDTO;
import com.qqw.supermarket.service.AccountService;
import com.qqw.supermarket.util.MD5Util;
import com.qqw.supermarket.util.ResultData;
import com.qqw.supermarket.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Classname AccountServiceImpl
 * @Description TODO()
 * @Date 2020/2/10 16:55
 * @Author by Alex
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Resource
    private RoleDao roleDao;
    /**
     * 登录校验
     * @param username
     * @param password 密码
     * @return
     */
    @Override
    public ResultData login(String username, String password) {
        AccountDTO account = accountDao.selectByAccount(username);

        //判断账户是否存在
        if(!StringUtils.isEmpty(account)) {

            String inPass = MD5Util.encode(password,account.getSalt());
            //判断密码是否一致
            if(inPass.equals(account.getPassword())){
                //判断账号是否被锁定
                if(!account.getLocked()){
                    //设置上次登录时间
                    Account account1 = new Account();
                    account1.setId(account.getId());
                    account1.setLastLoginAt(new Date());
                    accountDao.updateAccount(account1);
                    return new ResultData(1,"登录成功",account);
                }else {
                    return new ResultData(0,"用户已被锁定");
                }
            }
        }
        return new ResultData(0,"用户或密码错误");
    }

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    @Override
    public AccountDTO findByAccount(String name) {
        return accountDao.selectByAccount(name);
    }

    /**
     * 查询全部用户
     * @param params
     * @return
     */
    @Override
    public List<AccountDTO> findAll(Map<String, Object>params) {
        return accountDao.selectAll(params);
    }

    /**
     * 查询用户数量
     * @return
     */
    @Override
    public int count() {
        return accountDao.count();
    }

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    @Override
    public List<AccountDTO> findAllByPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum,pageSize);
        return accountDao.selectAll(params);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public AccountDTO getById(String id) {
        return accountDao.selectById(id);
    }

    /**
     * 更新用户信息
     * @param account
     * @return
     */
    @Override
    public int updateAccount(Account account) {
        return accountDao.updateAccount(account);
    }

    /**
     * 保存用户
     * @param account
     */
    @Override
    public int saveAccount(Account account) {
        account.setId(UUIDUtil.createUUID());
        String salt = account.getAccount();
        account.setSalt(salt);
        account.setPassword(MD5Util.encode("123456",salt));
        return  accountDao.insertAccount(account);
    }

    @Override
    public int deleteAccount(String id) {
        return accountDao.deleteById(id);
    }


    /**
     * 批量删除
     * @param arr
     */
    @Override
    public int batchdelete(String[] arr) {
        List list = new ArrayList();
        for (int i = 0 ; i < arr.length; i ++){
            list.add(arr[i]);
        }
       return accountDao.batchDelete(list);
    }

    /**
     * 用户授权
     * @param id
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData editRole(String id, Integer[] ids) {

        //删除此账号已有的权限
        roleDao.deleteAccountRole(id);
        if (ids == null || ids.length == 0){
            return new ResultData( 1,"修改授权成功", null);
        }
        Map<String,Object> map = new HashMap<>();
        //账号id
        map.put("id", id);
        //权限的id数组
        map.put("ids", ids);
        return roleDao.insertAccountRole(map) > 0 ?
                new ResultData( 1,"授权成功", null)
                : new ResultData( 0,"授权失败，请重试", null);
    }
}
