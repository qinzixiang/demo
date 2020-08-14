package com.qinzx.demo.jvm.chapter02;

/**
 * 被动引用示例2：
 * 创建数组类没有初始化，这段代码触发了一个[Lcom.qinzx.demo.jvm.chapter2.SuperClass]的类的初始化
 * 阶段，对于用户代码来说，这并不是一个合法的类名称，它是一个由虚拟机自动生成的、直接继承
 * 于java.lang.Object的子类，创建动作由字节码指令anewarray触发。这个类代表了一个元素类
 * 型为com.qinzx.demo.jvm.chapter2.SuperClass的一维数组，数组中应有的属性和方法（用户
 * 可直接使用的只有被修饰为public的length属性和clone()方法）都实现在这个类里。Java语言
 * 中对数组的访问比C/C++相对安全，因为这个类包装了数组元素的访问对象，而C/C++直接翻译为对
 * 数组指针的移动。
 * @author qinzx
 * @date 2020/02/26 17:10
 */
public class NotInitializationTest2 {
    public static void main(String[] args){
        SuperClass[] sca = new SuperClass[10];
    }
}