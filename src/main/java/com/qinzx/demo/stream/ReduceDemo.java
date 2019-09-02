package com.qinzx.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 缩减操作：
 * 何为缩减操作？
 * 我们先考虑一下min()和max()，这两个方法我们在第一篇和第二篇中均有提到，其中min()是返回流中的最小值，而max()返回流中最大值，
 * 前提是他们存在。他们之间的特点是什么？①都返回了一个值②由一可知，他们是终端操作。如果我们用流API的术语来形容前面这两种特性的
 * 结合体的话，它们代表了缩减操作。因为每个缩减操作都把一个流缩减为一个值，好比最大值，最小值。当然流API，把min()和max()，count()
 * 这些操作称为特例缩减。即然说到了特例，肯定就有泛化这种概念了，他就是reduce()方法了
 *
 * @ClassName: ReduceDemo
 * @Author qinzx
 * @Date 2019/07/11 15:59
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class ReduceDemo {
    public static void main(String[] args) {
        learnStream();
    }


    private static void learnStream() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        Optional<Integer> sum = lists.stream().reduce((a, b) -> a + b);
        if (sum.isPresent()) System.out.println("list的总和为:" + sum.get());
        //21
        //<====> lists.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);

        Integer sum2 = lists.stream().reduce(10, (a, b) -> a + b);
        //21
        System.out.println("list的总和为:" + sum2);

        Optional<Integer> product = lists.stream().reduce((a, b) -> a * b);
        if (product.isPresent()) System.out.println("list的积为:" + product.get());
        //720

        Integer product2 = lists.stream().reduce(2, (a, b) -> a * b);
        System.out.println("list的积为:" + product2);
        //720

        Integer product3 = lists.stream().reduce(1, (a, b) -> {
            if (b % 2 == 0)
                return a * b;
            else
                return a;
            //这里你可以为所欲为!
        });
        System.out.println("list的偶数的积为:" + product3);
        //48
    }
}