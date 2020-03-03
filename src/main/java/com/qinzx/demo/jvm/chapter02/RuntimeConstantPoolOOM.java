package com.qinzx.demo.jvm.chapter02;

/**
 * JDK6和JDK7、8的结果不同，是因为第一次加载一个字符串，这个字符串如果不在常量池中，JDK6会复制这个字符串实例到永久代的
 * 字符串常量池中存储，二JDK7、8只是会将这个字符串实例的引用保存到字符串常量池中。
 * @author qinzx
 * @date 2020/03/03 12:47
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args){
        //str1.intern()获取到的是保存在常量池中的java堆中对象实例的引用，所以是true
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        //str2.intern()获取到的是保存在常量池中的字符串对象，“java”字符串在创建str2字符串实例前已经加载到常量池中，
        // 所以返回的常量池中的对象引用，因此是false
        //java虚拟机加载sun.misc.Version类时，把“java”字符串加载到了字符串常量池中
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}