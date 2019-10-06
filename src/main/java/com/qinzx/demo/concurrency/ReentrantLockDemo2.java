package com.qinzx.demo.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁申请等待限时：避免死锁的一种方法
 * tryLock()方法立刻返回当前获取情况。
 *
 * tryLock(long time, TimeUnit unit)等待一定的时间，返回获取情况
 *
 * @ClassName: ReentrantLockDemo2
 * @Author qinzx
 * @Date 2019/07/13 13:59
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class ReentrantLockDemo2 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "获取当前锁");
                TimeUnit.SECONDS.sleep(4);
            } else {
                System.out.println(Thread.currentThread().getName()+ " 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args){
        ReentrantLockDemo2 reentrantLockDemo = new ReentrantLockDemo2();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
    }
}