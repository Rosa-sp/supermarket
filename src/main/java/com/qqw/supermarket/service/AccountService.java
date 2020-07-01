package com.qqw.supermarket.service;

import com.qqw.supermarket.model.Account;
import com.qqw.supermarket.model.AccountDTO;
import com.qqw.supermarket.util.ResultData;

import java.util.List;
import java.util.Map;

/**
 * @Classname AccountService
 * @Description TODO()
 * @Date 2020/2/10 16:55
 * @Author by Alex
 */
public interface AccountService {
    /**
     * 根据账户和密码查询账户信息
     * @param account 账号
     * @param password 密码
     * @return
     */
    ResultData login(String account, String password);

    /**
     * 根据账号查询用户
     * @param name
     * @return
     */
    AccountDTO findByAccount(String name);

    /**
     * 查询全部信息
     * @return
     */
    List<AccountDTO> findAll(Map<String, Object> params);

    /**
     *
     * @return
     */
    int count();

    List<AccountDTO> findAllByPage(int pageNum, int pageSize, Map<String, Object> params);


    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    AccountDTO getById(String id);

    /**
     * 更改用户信息
     * @param account
     * @return
     */
    int updateAccount(Account account);

    /**
     * 保存用户
     * @param account
     */
    int saveAccount(Account account);

    /**
     * 删除用户
     * @param id
     */
    int deleteAccount(String id);

    /**
     * 批量删除
     */
    int batchdelete(String[] arr);


    /**
     * 授权
     * @param id
     * @param ids
     * @return
     */
    ResultData editRole(String id, Integer[] ids);

}
