package com.qinzx.demo.concurrency;

import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：ReadWriteLock是jdk5提供的读写分离锁，可以有效的减少锁竞争，提升系统性能
 *
 * 以下示例：模拟读写分离锁对性能的提升
 */
public class ReentrantReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }
    public void handleWrite(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("开始执行");
        final ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        Runnable readRunnable = new Runnable(){
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable(){
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
//                    demo.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            Thread thread = new Thread(readRunnable);
            thread.start();
//            thread.join();
        }
        for (int i = 18; i < 20; i++) {
            Thread thread = new Thread(writeRunnable);
            thread.start();
//            thread.join();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
