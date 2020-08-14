package com.qinzx.demo.jvm.chapter02;

import java.io.Serializable;

/**
 * 重载选择最合适的方法的示例：
 *
 * @author qinzx
 * @date 2020/02/28 14:31
 */
public class OverloadTest {

//    public static void sayHello(Object arg) {
//        System.out.println("Hello Object");
//    }

//    public static void sayHello(int arg) {
//        System.out.println("hello int");
//    }

//    public static void sayHello(long arg) {
//        System.out.println("hello long");
//    }

//    public static void sayHello(Character arg) {
//        System.out.println("hello Character");
//    }

//    public static void sayHello(char arg) {
//        System.out.println("hello char");
//    }

    public static void sayHello(char... arg) {
        System.out.println("hello char...");
    }

//    public static void sayHello(Serializable arg) {
//        System.out.println("hello Serializable");
//    }

//    public static void sayHello(Comparable<Character> arg) {
//        System.out.println("hello Comparable");
//    }

    public static void main(String[] args) {
        /**
         * 输出 “hello char”,'a'是一个char类型的数据
         * 如果注释掉sayHello(char arg)方法，输出“hello int”,'a'除了可以代表一个字符串外，
         * 还可以代表数字65，因此参数类型为int的重载也可以，
         * 继续注释掉sayHello(int arg)方法，输出“hello long”，这时发生两次自动类型转换，'a'
         * 转换为65之后，进一步转换为65L，而且还可以继续转换很多次，
         * 按照 char -> int -> long -> float -> double 的顺序转型进行匹配，但不会匹配到
         * byte和short类型的重载，因为char到byte或short的转型是不安全的。
         * 我们继续注释掉sayHello(long arg)方法，那么输出“hello Character”，这时发生了一次
         * 自动装箱，char装箱为java.lang.Character，
         * 继续注释掉sayHello(Character arg)方法，输出“hello Serializable”，因为java.lang.Serializable
         * 是java.lang.Character类实现的一个接口，自动装箱后还是找不到对应的方法，但是找到了
         * 装箱类实现了的接口类型，所以又发生了一次自动转型。char可以转型成int，但是Character是
         * 绝对不会转型为Integer的，它只能安全地转型为它实现的接口或父类。Character还实现了另外
         * 一个接口java.lang.Comparable<Character>，如果同时出现两个参数分别为Serializable和
         * Comparable<Character>的重载方法，那么它们此时的优先级是一样的。编译器无法确定要自动
         * 转型为哪种类型，会提示类型模糊，拒绝编译。程序必须调用时显示地指定字面量的静态类型，如
         * sayHello((Comparable<Character>) 'a')，才能通过编译。
         * 继续注释sayHello(Serializable arg)方法，输出“hello Object”，这时是char装箱后转型为
         * 父类了，如果有多个父类，那么将在继承关系中从下往上开始搜索，越接近上层的优先级越低。即使
         * 方法调用传入的参数值为null，这个规则仍然适用。
         * 继续注释sayHello(Object arg)，输出“hello char...”，变长参数的重载优先级是最低的，这
         * 时候字符'a'被当作了一个数组元素。
         *
         * 重载中选择最合适方法的过程，可参见Java语言规范的 15.12.2.5. Choosing the Most Specific Method 章节
         * https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.12.2.5
         *
         */
        sayHello('a');
    }
}