package com.qinzx.demo.java8.date;

import cn.hutool.core.date.DateUtil;

/**
 * 时间转换一定要注意format字符串不能写错
 * @ClassName: OldDateDemo
 * @Author qinzx
 * @Date 2019/12/31 10:08
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class OldDateDemo {
    public static void main(String[] args){
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "YYYY-MM-dd hh:mm:ss"));
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "yyyy-MM-dd hh:mm:ss"));
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "YYYY-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.parse("2019-12-31 10:00:05", "yyyy-MM-dd HH:mm:ss"));
    }
}