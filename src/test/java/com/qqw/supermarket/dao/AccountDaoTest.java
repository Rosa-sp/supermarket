package com.qqw.supermarket.dao;

import com.qqw.supermarket.model.Account;
import com.qqw.supermarket.util.MD5Util;
import com.qqw.supermarket.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Classname AccountDaoTest
 * @Description TODO()
 * @Date 2020/2/1 11:04
 * @Author by Alex
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountDaoTest {

    @Resource
    private AccountDao accountDao;


    @Test
    public void insertAccount() {
        Account account = new Account();
        account.setId(UUIDUtil.createUUID());
        String a = "admin1";
        account.setAccount(a);
        account.setPassword(MD5Util.encode("123456",a));
        account.setRealName("夏树");
        account.setSalt(a);
        accountDao.insertAccount(account);
        System.out.println(account.getPassword());
    }
}