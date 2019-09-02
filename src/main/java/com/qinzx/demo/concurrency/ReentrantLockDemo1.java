package com.qinzx.demo.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo1
 * @Author qinzx
 * @Date 2019/07/13 13:49
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class ReentrantLockDemo1 implements  Runnable {

    private Lock lock = new ReentrantLock();
    
    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "running");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "finished");
            lock.unlock();
        }catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"interupted");
        }
    }
    
    public static void main(String[] args){
        ReentrantLockDemo1 reentrantLockDemo = new ReentrantLockDemo1();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
        thread02.interrupt();
    }
}