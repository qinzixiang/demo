package com.qinzx.demo.jvm.chapter05;

/**
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-02-16 21:55
 **/
public class DynamicLinkingTest {

    public void methodA() {
        System.out.println("methodA()...");
    }

    public void methodB() {
        System.out.println("methodB()...");

        methodA();
    }
}
