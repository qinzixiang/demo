package com.qinzx.demo.java8.date;

import cn.hutool.core.date.DateUtil;

/**
 * 时间转换一定要注意format字符串不能写错
 * @author qinzx
 * @date 2019/12/31 10:08
 */
public class OldDateDemo {
    public static void main(String[] args){
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "YYYY-MM-dd hh:mm:ss"));
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "yyyy-MM-dd hh:mm:ss"));
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "YYYY-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "yyyy-MM-dd HH:mm:ss"));
    }
}