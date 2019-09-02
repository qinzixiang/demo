package com.qinzx.demo.concurrency;

/**
 * 3.修饰静态块
 *
 * 从下面的例子中可以看出，是将synchronized作用于一个给定的实例对象instance，当前实例对象就是锁对象，
 * 每次线程进入synchronized的代码块时就要求当前线程必须拿到instance实例的对象所，其他的线程就必须等待。
 *
 * @ClassName: SynchronizedDemo3
 * @Author qinzx
 * @Date 2019/07/09 15:25
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class SynchronizedDemo3 implements Runnable{
    public static int num = 0;

    static SynchronizedDemo3 instance = new SynchronizedDemo3();

    @Override
    public void run() {
        synchronized(instance) {
            for(int i = 0; i< 5; i++) {
                num++;
                System.out.println("Thread-Id:"+Thread.currentThread().getId()+" num="+num);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num="+num);
        /**num=10*/

    }
}