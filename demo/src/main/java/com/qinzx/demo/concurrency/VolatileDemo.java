package com.qinzx.demo.concurrency;

import com.qinzx.demo.DemoApplication;

import java.util.concurrent.TimeUnit;

/**
 * volatile是一个类型修饰符，他主要用于修饰被不同的线程访问和修改的变量，他是作为指令关键字，
 * 确保本条指令不会因编译器的优化而省略，且要求每次直接读值。
 *
 * 可以确保将变量的更新操作通知到其他的线程，当把变量声明为volatile类型，编译与运行时都会注意
 * 到这个变量是共享的，因此该变量不会被缓存在寄存器或者对其他处理器不可见的地方，因此在读取volatile
 * 类型的变量时总会返回最新写入的值。
 *
 * 在访问volatile变量时不会执行加锁操作，因此也就不会执行线程阻塞，他是一种比synchronized关键字
 * 更轻量级的同步机制。
 *
 * volatile的特性：可见性，不能保证原子性
 * https://www.jianshu.com/p/2cfd551055d7
 * @author qinzx
 * @date 2019/06/05 16:46
 */
class MyData{
    volatile int number = 0;

    void addTo60() {
        this.number = 60;
    }
}
public class VolatileDemo {
    private int inc = 0;

    /**
     * 同步执行方法，解决方案太重了，可以使用AtomicInteger对象
     */
    private synchronized void increase() {
        inc++;
    }

    public static void main(String[] args){
        final VolatileDemo test = new VolatileDemo();
        for(int i=0;i<20;i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                }
            }).start();
        }
        //判断前面创建的所有线程都关闭之后，退出程序，否则继续等待
        // 剩余活动线程为main线程和gc守护线程，执行完毕之后系统活动线程数量可能因为守护线程的存在导致死循环，
        while (Thread.activeCount() > 2) {
//            System.out.println(Thread.activeCount());
            //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(test.inc);
    }
    //volatile可以保证可见性示例
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number");
        },"AAA").start();
        while (myData.number == 0) {
            //main线程一直等待
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}