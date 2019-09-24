package com.hex.springcloudorder.utils;

import java.util.Random;

/**
 * User: hexuan
 * Date: 2019/9/20
 * Time: 2:53 PM
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
