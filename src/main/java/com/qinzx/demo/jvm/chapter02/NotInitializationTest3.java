package com.qinzx.demo.jvm.chapter02;

/**
 * 被动使用示例3：
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接饮用到定义常量的类，
 * 因此不会触发定义常量的类的初始化。
 * 此类的class文件中并没有SuperClass类的符号引用入口，这两个类在编译成
 * Class之后就不存在任何联系了。
 * @author qinzx
 * @date 2020/02/26 17:32
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class NotInitializationTest3 {
    public static void main(String[] args){
        System.out.println(SuperClass.finalValue);
    }
}