package com.qinzx.demo.jvm.part5;

/**
 * DCL（Double Check Lock）单例：
 * voaltile关键字禁止指令重排序优化，支持了单例
 * 参考：https://www.cnblogs.com/codingmengmeng/p/9846131.html
 * @author : qinzx
 *
 * @create : 2020-03-26 21:30
 **/
public class Singleton {
    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
