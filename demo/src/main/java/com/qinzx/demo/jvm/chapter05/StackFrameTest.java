package com.qinzx.demo.jvm.chapter05;

/**
 * 演示方法调用，当前栈桢的切换
 * @author : qinzx
 * @create : 2020-02-16 14:26
 */
public class StackFrameTest {
    public static void main(String[] args) {
        StackFrameTest stackFrameTest = new StackFrameTest();
        stackFrameTest.method1();
    }

    public void method1() {
        System.out.println("method1()开始执行");
        method2();
        System.out.println("method1()执行结束");
    }

    private int method2() {
        System.out.println("method2()开始执行");
        int i = 10;
        int m = (int) method3();
        System.out.println("method2()即将结束");
        return i + m;
    }

    private double method3() {
        System.out.println("method3()开始执行");
        double j = 20.0;
        System.out.println("method3()即将结束");
        return j;
    }
}
