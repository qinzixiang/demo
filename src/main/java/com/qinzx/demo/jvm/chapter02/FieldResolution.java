package com.qinzx.demo.jvm.chapter02;

/**
 * 子单解析过程：
 * 如果有一个同名字段同时出现在类的父类和实现的接口中，则报错
 * @author qinzx
 * @date 2020/02/27 10:50
 */
public class FieldResolution {
    interface Interface0{
        int A = 0;
    }

    interface Interface1 extends Interface0{
        int A = 1;
    }

    interface Interface2{
        int A = 2;
    }

    static class Parent implements Interface1{
        public static int A = 3;
    }

    static class Son extends Parent implements Interface1{
        public static int A = 4;  //此代码注释后，编译器提示“The field Son.A is ambiguous”
    }
    public static void main(String[] args){
        System.out.println(Son.A);
        System.out.println(Parent.A);
    }
}