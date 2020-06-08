package com.qinzx.demo.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁特性：中断响应
 * 对于synchronized来说，如果一个线程在等待锁，结果只有两种情况：要么获得这把锁继续执行，
 * 要么就保持等待。使用重入锁，提供另一种可能，线程可以被中断。也就是在等待锁的过程中，程序可以
 * 根据需要取消对锁的请求。
 * 下面示例：展示如何在死锁的时候中断线程
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     * @param lock
     */
    public IntLock(int lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();  //使用此方法引起的锁申请动作可以对中断进行响应
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        IntLock intLock1 = new IntLock(1);
        IntLock intLock2 = new IntLock(2);
        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //中断其中一个线程
        t2.interrupt();

    }
}
