package com.qinzx.demo.jvm.chapter02;

/**
 * 被动引用示例1：通过子类引用父类的静态字段，不会导致子类初始化。
 * 至于是否要触发子类的加载和验证，在虚拟机规范中并未明确规定，取决于虚拟机的具体实现
 * @author qinzx
 * @date 2020/02/26 17:05
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class NotInitializationTest {
    public static void main(String[] args){
        System.out.println(SubClass.value);
    }
}
class SuperClass{
    static{
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
    public static final int finalValue = 123;
}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init!");
    }
}