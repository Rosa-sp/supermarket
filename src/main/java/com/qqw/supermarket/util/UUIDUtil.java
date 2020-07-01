package com.qqw.supermarket.util;

import java.util.UUID;

/**
 * @Classname UUIDUtil
 * @Description UUID工具类，包括生成36位UUID及32位UUID
 * @Date 2020/2/10 17:03
 * @Author by Alex
 */
public class UUIDUtil {

    private static String UUID_SPLIT = "-";

    private static String EMPTY_STRING = "";

    /**
     * 生成36位UUID
     *  UUID中包含,字符 '-'
     * @return uuid
     */
    public static String create(){
        return UUID.randomUUID().toString();
    }

    /**
     * 生成32为UUID
     * 不包含字符 '-'
     * @return uuid
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replace(UUID_SPLIT,EMPTY_STRING);
    }


    /**
     * 生成10位UUID
     * 纯数字
     * @return
     */
    public static String numberUUID(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        String uu = String.valueOf(orderId);
        return uu;
    }

}
