package com.qqw.supermarket.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Classname MD5Util
 * @Description TODO()
 * @Date 2020/2/10 17:09
 * @Author by Alex
 */
public class MD5Util {

    /**
     * 密码加密
     * @param password
     * @return
     */
    public static String encode(String password){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            return toString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 密码和盐值加密
     * @param password
     * @param salt
     * @return
     */
    public static String encode(String password,String salt){
        return encode(password + salt);
    }

    private static String toString(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            //0xff为1111 1111 得到的结果(2字节)将其转换为16进制
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16){
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }
}
