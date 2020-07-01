package com.qqw.supermarket.dao;


import com.qqw.supermarket.model.Account;
import com.qqw.supermarket.model.AccountDTO;

import java.util.List;
import java.util.Map;


public interface AccountDao {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 保存
     * @param record
     * @return
     */
    int insertAccount(Account record);

    /**
     * 查询
     * @param id
     * @return
     */
    AccountDTO selectById(String id);

    List<AccountDTO> selectByRId(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateAccount(Account record);

    /**
     *根据账号查询
     * @param name
     * @return
     */
    AccountDTO selectByAccount(String name);

    /**
     * 查询全部
     * @return
     */
    List<Account> listAccount(Map<String, Object> params);


    List<AccountDTO> selectAll(Map<String, Object> params);
    /**
     * 查询有多少条数据
     * @return
     */
    int count();

    /**
     *分页
     * @param params
     * @return
     */
    List<AccountDTO> listAccountByPage(Map<String, Object> params);

    /**
     * 批量删除
     * @param list
     */
    int batchDelete(List list);

}