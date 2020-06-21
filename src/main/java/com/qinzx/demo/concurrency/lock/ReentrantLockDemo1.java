package com.qinzx.demo.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断
 * 等待线程的等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。 ReentrantLock.lock方法
 * 不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。
 * 只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程。
 *
 * @author qinzx
 * @date 2019/07/13 13:49
 */
public class ReentrantLockDemo1 implements  Runnable {

    private Lock lock = new ReentrantLock();
    
    @Override
    public void run() {
        try {
//            lock.lockInterruptibly(); //Thread.interrupt()调用后中断等待获取锁的线程的等待，直接返回，会抛出一个InterruptedException
            lock.lock();    //Thread.interrupt()调用后不能中断等待获取锁的状态，会一直等待，直到获取锁成功后把当前线程置为interrupted状态,然后再中断线程。
            System.out.println(Thread.currentThread().getName() + "running");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "finished");
        }catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"interupted");
        }finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args){
        ReentrantLockDemo1 reentrantLockDemo = new ReentrantLockDemo1();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
        thread02.interrupt();   //thread02运行中，会将线程中断掉，抛出中断异常
    }
}