package com.qinzx.demo.concurrency;

/**
 * 2.修饰静态方法
 * 当synchronized作用与静态方法时，锁是当前类的class对象。
 *
 * 由于静态方法是类成员，因此通过class对象锁可以控制静态成员的并发操作。如果一个线程A调用一个实例对象的非静态同步方法，
 * 线程B调用这个实例对象所属的类的静态同步方法，他们之间是不存在互斥的，因此两个线程占用的锁是不一样的。
 * 但是如果两个方法共同操作同一个静态变量num，那么还是需要考虑线程安全的问题。
 *
 * @ClassName: SynchronizedDemo2
 * @Author qinzx
 * @Date 2019/07/09 15:20
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class SynchronizedDemo2 implements Runnable{
    public static int num = 0;

    /**
     * 非静态同步方法，占用的锁是当前实例对象的锁
     */
    public synchronized void incr1() {
        num++;
        System.out.println("Thread-Id:"+Thread.currentThread().getId()+" num="+num);
    }

    /**
     * 静态同步方法，占用的锁是当前类的锁
     */
    public static synchronized void incr() {
        num++;
        System.out.println("Thread-Id:"+Thread.currentThread().getId()+" num="+num);
    }

    @Override
    public void run() {
        for(int i = 0; i< 100; i++) {
            incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo2 test1 = new SynchronizedDemo2();
        SynchronizedDemo2 test2 = new SynchronizedDemo2();
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num="+num);
        /**num=10*/
    }
}