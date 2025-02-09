package com.qinzx.demo.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁，完全可以替代synchronized关键字
 *
 * @author qinzx
 * @date 2019/07/13 13:42
 */
public class ReentrantLockDemo implements Runnable {
    private Lock lock = new ReentrantLock();
    private static int tickets = 200;

    @Override
    public void run() {
        while (true) {
            //获取锁
            lock.lock();
            try {
                if (tickets > 0) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " " + tickets--);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(reentrantLockDemo, "thread" + i);
            thread.start();
        }
    }

}