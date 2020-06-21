package com.qinzx.demo.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计数器，
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch end = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国，被灭");
                end.countDown();
            }, CountryEnum.getByCode(i + 1).getValue()).start();
        }
        end.await();
        System.out.println("秦国称霸！");
    }

    private static void fireRocket() throws InterruptedException {
        CountDownLatch end = new CountDownLatch(10);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                try {
                    //模拟检查任务
                    Thread.sleep(new Random().nextInt(10) * 1000);
                    System.out.println(Thread.currentThread().getId() + "检查完毕");
                    end.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //等待检查,主线程等待10个检查任务全部完成
        end.await();
        //发射火箭
        System.out.println("fire！");
        exec.shutdown();
    }
}
