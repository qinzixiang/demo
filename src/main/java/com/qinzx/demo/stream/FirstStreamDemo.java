package com.qinzx.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ClassName: FirstStreamDemo
 * @Author qinzx
 * @Date 2019/07/11 14:11
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class FirstStreamDemo {
    public static void main(String[] args) {
        learnStream();
    }

    private static void learnStream() {
        //首先，创建一个1-6乱序的List
        List<Integer> lists = new ArrayList<>();
        lists.add(4);
        lists.add(3);
        lists.add(6);
        lists.add(1);
        lists.add(5);
        lists.add(2);

        //看看List里面的数据是什么样子的先
        System.out.print("List里面的数据:");
        for (Integer elem : lists) System.out.print(elem + " ");// 4 3 6 1 5 2

        System.out.println();
        new ArrayList<>().stream();
        //最小值
        System.out.print("List中最小的值为:");
        Stream<Integer> stream = lists.stream();
        Optional<Integer> min = stream.min(Integer::compareTo);
        if (min.isPresent()) {
            System.out.println(min.get());//1
        }


        //最大值
        System.out.print("List中最大的值为:");
        lists.stream().max(Integer::compareTo).ifPresent(System.out::println);//6

        //排序
        System.out.print("将List流进行排序:");
        Stream<Integer> sorted = lists.stream().sorted();
        sorted.forEach(elem -> System.out.print(elem + " "));// 1 2 3 4 5 6

        System.out.println();

        //过滤
        System.out.print("过滤List流，只剩下那些大于3的元素:");
        lists.stream()
                .filter(elem -> elem > 3)
                .forEach(elem -> System.out.print(elem + " "));// 4 5 6

        System.out.println();

        //过滤
        System.out.println("过滤List流，只剩下那些大于0并且小于4的元素:\n=====begin=====");
        lists.stream()
                .filter(elem -> elem > 0)
                .filter(elem -> elem < 4)
                .sorted(Integer::compareTo)
                .forEach(System.out::println);// 1 2 3

        System.out.println("=====end=====");
        //经过了前面的这么多流操作，我们再来看看List里面的值有没有发生什么改变
        System.out.print("原List里面的数据:");
        for (Integer elem : lists) System.out.print(elem + " ");// 4 3 6 1 5 2
    }
}