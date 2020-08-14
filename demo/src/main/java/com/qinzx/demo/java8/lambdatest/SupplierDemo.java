package com.qinzx.demo.java8.lambdatest;

import java.util.function.Supplier;

/**
 * java.util.function.Supplier<T> 接口仅包含一个无参的方法： T get() 。用来获取一个泛型参数指定类型的对 象数据。
 * 由于这是一个函数式接口，这也就意味着对应的Lambda表达式需要“对外提供”一个符合泛型类型的对象数据。
 *
 * 顾名思义,这是一个供应商,提供者.就如一个工厂一样.
 *
 * 常用的函数式接口
 *     java.util.function.Supplier<T>接口仅包含一个无参的方法：T get()。用来获取一个泛型参数指定类型的对象数据。
 *     Supplier<T>接口被称之为生产型接口,指定接口的泛型是什么类型,那么接口中的get方法就会生产什么类型的数据
 */
public class SupplierDemo {
    //定义一个方法,方法的参数传递Supplier<T>接口,泛型执行String,get方法就会返回一个String
    public static String getString(Supplier<String> sup){
        return sup.get();
    }
    public static void main(String[] args) {
        //调用getString方法,方法的参数Supplier是一个函数式接口,所以可以传递Lambda表达式
        String s = getString(new Supplier<String>() {
            @Override
            public String get() {
                return "匿名内部类胡歌";
            }
        });
        System.out.println(s);

        String s1 = getString(()->{
            //生产一个字符串,并返回
            return "lambda胡歌";
        });
        System.out.println(s1);
        //优化Lambda表达式
        String s2 = getString(()->"优化后lambda胡歌");
        System.out.println(s2);
    }
}
