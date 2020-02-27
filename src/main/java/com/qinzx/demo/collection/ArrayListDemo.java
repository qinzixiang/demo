package com.qinzx.demo.collection;

import cn.hutool.core.lang.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试list
 * 1.contains方法
 * 可以判断，空字符串和null是否在集合中
 *
 * @ClassName: ArrayListDemo
 * @Author qinzx
 * @Date 2019/09/17 13:48
 */
public class ArrayListDemo {
    public static void main(String[] args){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("");
        strings.add(null);
        Assert.isTrue(strings.contains(""));
        Assert.isTrue(strings.contains(null));
        ArrayList<Long> orgIds = new ArrayList<>();
        orgIds.add(11L);
        orgIds.add(121L);
        orgIds.add(56L);
        orgIds.add(121L);
        orgIds.add(19L);
        System.out.println(orgIds);
        List<Long> newOrgIds = new ArrayList<>();
        newOrgIds.add(121L);
        newOrgIds.add(11L);
        newOrgIds.add(121L);
        //取交集不去重，结果中会出现重复的元素
        orgIds.retainAll(newOrgIds);
        System.out.println(orgIds);
        //只删除匹配的第一个元素
//        orgIds.remove(121L);
        //java8 新增的，删除使括号内的函数为true的元素
        orgIds.removeIf(Long.valueOf(121)::equals);

        System.out.println(orgIds);
        //空集合可以调用contains
        ArrayList<Long> emptyLongList = new ArrayList<>();
        boolean contains = emptyLongList.contains(1232L);
        Assert.isFalse(contains);
    }
}