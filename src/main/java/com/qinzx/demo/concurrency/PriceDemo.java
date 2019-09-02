package com.qinzx.demo.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 异步查询商品价格
 * @ClassName: PriceDemo
 * @Author qinzx
 * @Date 2019/07/11 11:02
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class PriceDemo {
    private List<Shop> shops = Arrays.asList(new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8")
    );

    public List<String> findPrices(String product){
        /*
         * 方法一：加并行流.parallel()
         * */
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f ",shop.getName(),shop.getPice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 使用CompletableFutrue异步执行查询任务
     * @author  qinzx
     * @date  2019/7/11 11:04
     * @param product
     * @return  java.util.List<java.lang.String>
     */
    public List<String> findPricesNew(String product){
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product))))
                .collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    public static void main (String[] args){
        PriceDemo priceDemo = new PriceDemo();
        Long start = System.currentTimeMillis();
        System.out.println(priceDemo.findPrices("苹果x"));
        System.out.println("服务耗时："+(System.currentTimeMillis()-start));
        //使用CompletableFuture
        PriceDemo priceDemo1 = new PriceDemo();
        Long start1 = System.currentTimeMillis();
        System.out.println(priceDemo1.findPricesNew("苹果x"));
        System.out.println("服务耗时："+(System.currentTimeMillis()-start1));
    }

}