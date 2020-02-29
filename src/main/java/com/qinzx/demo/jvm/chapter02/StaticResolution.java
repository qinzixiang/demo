package com.qinzx.demo.jvm.chapter02;

/**
 * 方法静态解析演示
 *
 * @author qinzx
 * @date 2020/02/27 20:35
 */
public class StaticResolution {

    public static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args){
        StaticResolution.sayHello();
    }

}