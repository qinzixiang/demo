package com.qinzx.demo.collection;

import cn.hutool.core.lang.Assert;

import java.util.ArrayList;

/**
 * 测试list
 * 1.contains方法
 * 可以判断，空字符串和null是否在集合中
 *
 * @ClassName: ArrayListDemo
 * @Author qinzx
 * @Date 2019/09/17 13:48
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class ArrayListDemo {
    public static void main(String[] args){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("");
        strings.add(null);
        Assert.isTrue(strings.contains(""));
        Assert.isTrue(strings.contains(null));

    }
}