package com.qinzx.demo.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = new Random().nextInt(6);
        System.out.println(Thread.currentThread().getName() + " ******* come in callable  :" + i);
        TimeUnit.SECONDS.sleep(i);
        return 1024;
    }
}

/**
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-06-21 16:52
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask3 = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask1, "t1");
        Thread t2 = new Thread(futureTask2, "t2");
        Thread t3 = new Thread(futureTask3, "t3");
        Thread t4 = new Thread(futureTask1, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println("********** result1:" + futureTask1.get());
        System.out.println("********** result2:" + futureTask2.get());
        System.out.println("********** result3:" + futureTask3.get());
        System.out.println("********** result4:" + futureTask1.get());
    }
}
