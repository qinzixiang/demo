package com.qinzx.demo.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁等待限时：不传参默认方式
 *
 */
public class TryLock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     * @param lock
     */
    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + ":My job done");
                                return;
                            } finally {
                                System.out.println(Thread.currentThread().getName()+"：释放锁2");
                                lock2.unlock();
                            }
                        }
                    } finally {
                        System.out.println(Thread.currentThread().getName()+"：释放锁1");
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName()+":My job done");
                                return;
                            }finally {
                                System.out.println(Thread.currentThread().getName()+"：释放锁1");
                                lock1.unlock();
                            }
                        }
                    }finally {
                        System.out.println(Thread.currentThread().getName()+"：释放锁2");
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        TryLock tryLock1 = new TryLock(1);
        TryLock tryLock2 = new TryLock(2);
        Thread thread1 = new Thread(tryLock1,"线程1");
        Thread thread2 = new Thread(tryLock2,"线程2");
        thread1.start();
        thread2.start();

    }
}
