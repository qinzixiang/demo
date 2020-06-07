package com.qinzx.demo.jvm.part5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子int型
 * Atomic 变量自增运算测试
 *
 * @author qinzx
 * @date 2020/03/27 15:06
 */
public class AtomicTest {
    public static AtomicInteger race = new AtomicInteger();

    public static void increase() {
        race.incrementAndGet();
    }

    public static final int THREADS_COUNT = 20;
    public static void main(String[] args){
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            System.out.println(Thread.activeCount()+":"+ AtomicTest.race);
            Thread.yield();
        }
        System.out.println(race);
    }
}