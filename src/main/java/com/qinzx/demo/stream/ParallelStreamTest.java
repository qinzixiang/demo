package com.qinzx.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 并行流使用
 * @author qinzx
 * @date 2019/07/11 11:22
 */
public class ParallelStreamTest {
    public static void main(String[] args){
        disposeStream();
    }

    private static void disposeStream() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        Optional<Integer> sum = lists.parallelStream().reduce((a, b) -> a + b);//这里把stream()换成了parallelStream（）
        if (sum.isPresent()) System.out.println("list的总和为:" + sum.get());//21
        //<====> lists.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);

        Integer sum2 = lists.stream().reduce(0, (a, b) -> a + b);//21
        System.out.println("list的总和为:" + sum2);

        Optional<Integer> product = lists.stream().reduce((a, b) -> a * b);
        if (product.isPresent()) System.out.println("list的积为:" + product.get());//720

        Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * b);//这里把stream()换成了parallelStream（）
        System.out.println("list的积为:" + product2);//720
    }
}