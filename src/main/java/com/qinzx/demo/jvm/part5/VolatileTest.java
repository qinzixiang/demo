package com.qinzx.demo.jvm.part5;

/**
 * volatile变量自增运算测试：演示volatile并发不安全
 * 虽然volatile在各个线程的工作内存中不存在一致性问题，但是Java里面的运算并非原子操作，导致volatile变量
 * 的运算在并发下一样是不安全的。
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-03-26 21:10
 **/
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }
    public static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
