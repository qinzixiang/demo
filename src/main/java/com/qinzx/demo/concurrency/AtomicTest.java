package com.qinzx.demo.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-06-07 15:06
 **/
public class AtomicTest {
    private int inc = 0;
    private AtomicInteger num = new AtomicInteger(0);

    private void increase() {
        inc++;
        num.getAndIncrement();
        num.compareAndSet(num.intValue(), num.intValue() + 1);
    }

    public static void main(String[] args) {
        AtomicTest atomicTest = new AtomicTest();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicTest.increase();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread() + "\t finally number valud:" + atomicTest.inc + " - " + atomicTest.num);
    }
}
