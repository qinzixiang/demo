package com.qinzx.demo.java8.lambdatest;

import java.util.function.Predicate;

/**
 * 有时候我们需要对某种类型的数据进行判断，从而得到一个boolean值结果。
 *
 * 这时可以使用 java.util.function.Predicate<T> 接口。
 *
 * Predicate接口中有三个默认方法，分别为 and, or, negate
 */
public class PredicateDemo {
    /*
     定义一个方法
     参数传递一个String类型的字符串
     传递一个Predicate接口,泛型使用String
     使用Predicate中的方法test对字符串进行判断,并把判断的结果返回
  */
    private static boolean checkString(String s, Predicate<String> pre){  //定义好方法
        return  pre.test(s);
    }

    private static boolean checkString(String s, Predicate<String> pre1, Predicate<String> pre2){  //定义好方法
        return  pre1.and(pre2).test(s);
    }
    public static void main(String[] args) {
        //定义一个字符串
        String s = "abcdef";
        // 匿名内部类方式
        boolean b1 = checkString(s, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()>5;
            }
        });
        System.out.println("匿名内部类方式： "+b1);
        //调用checkString方法对字符串进行校验,参数传递字符串和Lambda表达式
        boolean b2 = checkString(s,(String str)->{
            //对参数传递的字符串进行判断,判断字符串的长度是否大于5,并把判断的结果返回
            return str.length()>5;
        });
        System.out.println("lambda表达方式： "+b2);
        //优化Lambda表达式
        boolean b3 = checkString(s,str->str.length()>5);
        System.out.println("优化后lambda表达方式： "+b3);
    }
}
