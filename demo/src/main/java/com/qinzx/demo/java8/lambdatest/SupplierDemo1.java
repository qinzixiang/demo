package com.qinzx.demo.java8.lambdatest;

import java.util.function.Supplier;

/**
 * 求数组元素的最大值
 */
public class SupplierDemo1 {
    //定义一个方法,用于获取int类型数组中元素的最大值,方法的参数传递Supplier接口,泛型使用Integer
    public static int getMax(Supplier<Integer> sup){
        return sup.get();
    }
    public static void main(String[] args) {
        //定义一个int类型的数组,并赋值
        int[] arr = {100,0,-50,880,99,33,-30};
        // 匿名函数
        int maxvalue = getMax(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int max = arr[0];
                for (int i : arr){
                    if(i>max){
                        max = i;
                    }
                }
                return max;
            }
        });
        System.out.println("数组中元素的最大值是:"+maxvalue);
        //调用getMax方法,方法的参数Supplier是一个函数式接口,所以可以传递Lambda表达式
        int maxValue = getMax(()->{
            //获取数组的最大值,并返回
            //定义一个变量,把数组中的第一个元素赋值给该变量,记录数组中元素的最大值
            int max = arr[0];
            //遍历数组,获取数组中的其他元素
            for (int i : arr) {
                //使用其他的元素和最大值比较
                if(i>max){
                    //如果i大于max,则替换max作为最大值
                    max = i;
                }
            }
            //返回最大值
            return max;
        });
        System.out.println("数组中元素的最大值是:"+maxValue);
    }
}
