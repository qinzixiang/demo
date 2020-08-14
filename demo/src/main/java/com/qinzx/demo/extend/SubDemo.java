package com.qinzx.demo.extend;

import lombok.Data;

/**
 * 父类私有属性子类访问，可以通过public的getter/setter方法访问
 * @author qinzx
 * @date 2019/11/12 10:40
 */
@Data
public class SubDemo extends SuperDemo {
    private Integer d = 3;
    public static void main(String[] args){
        SubDemo subDemo = new SubDemo();
        System.out.println(subDemo.getA());
        System.out.println(subDemo.getB());
        System.out.println(subDemo.getC());
        System.out.println(subDemo.getD());
        System.out.println();
    }
}