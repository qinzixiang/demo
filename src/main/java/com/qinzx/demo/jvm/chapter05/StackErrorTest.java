package com.qinzx.demo.jvm.chapter05;

/**
 * 栈溢出演示，虚拟机栈空间设置大小
 *
 * @author : qinzx
 * @create : 2020-02-16 14:14
 * 默认情况下：count：11405
 * 设置栈的大小：-Xss256k ,count: 2470
 *
 */
public class StackErrorTest {
    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);
        count ++;
        main(args);
    }
}
