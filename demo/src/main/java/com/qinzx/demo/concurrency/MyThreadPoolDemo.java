package com.qinzx.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 前三种:继承Thread类；实现Runable接口：无返回值，不抛异常；实现Callable接口：有返回值，抛异常；
 * 第四种使用java多线程的方式：线程池
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-06-21 17:22
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //一池5个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池1线程
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        //一池N个线程
        ExecutorService threadPool2 = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "\t 处理业务"));
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }
}
