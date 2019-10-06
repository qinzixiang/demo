package com.qinzx.demo.java8.lambdatest;

import com.qinzx.demo.concurrency.ProducerConsumerTest;

import java.util.function.Consumer;

/**
 *
 */
public class ConsumerDemo {
    /*
        定义一个方法
        方法的参数传递一个字符串的姓名
        方法的参数传递Consumer接口,泛型使用String
        可以使用Consumer接口消费字符串的姓名
     */
    public static void method(String name, Consumer<String> con) {
        con.accept(name);
    }

    //使用Consumer的默认方法
    //定义一个方法，方法的参数传递一个字符串和两个Consumer接口，Consumer接口使用String泛型
    public static void methodAnd(String s, Consumer<String> con1, Consumer<String> con2) {
        //con1.accept(s);
        //con2.accept(s);
        //上下的写法效果一样
        //使用andThen方法，把两个Consumer接口连接到一起，再消费数据
        con1.andThen(con2).accept(s);
    }

    public static void main(String[] args) {
        // 匿名类方式
        method("赵丽颖", new Consumer<String>() {
            @Override
            public void accept(String name) {
                String reName = new StringBuffer(name).reverse().toString();
                System.out.println(reName);
            }
        });

        /**
         * 调用method方法,传递字符串姓名,方法的另一个参数是Consumer接口,是一个函数式接口,所以可以传递Lambda表达式
         * 输出：
         *      颖丽赵
         *      颖丽赵
         */

        method("赵丽颖", (String name) -> {
            //对传递的字符串进行消费
            //消费方式:直接输出字符串
            //System.out.println(name);
            //消费方式:把字符串进行反转输出
            String reName = new StringBuffer(name).reverse().toString();
            System.out.println(reName);
        });
        /**
         * 传递调用,对参数的修改，不会传递
         * 输出：
         *      Hellofirst
         *      Hellosecond
         */
        methodAnd("Hello", (s) -> {
            s += "first";
            System.out.println(s);
        }, (s) -> {
            s += "second";
            System.out.println(s);
        });
    }
}
