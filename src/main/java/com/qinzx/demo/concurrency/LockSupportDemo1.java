package com.qinzx.demo.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.unpartk()方法在park()方法前调用，线程可以正常执行
 */
public class LockSupportDemo1 {
    public static void main(String[] args) {
        UnpartThread myThread = new UnpartThread(Thread.currentThread());
        myThread.start();
        try {
            // 主线程睡眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }
}
class UnpartThread extends Thread {
    private Object object;

    public UnpartThread(Object object) {
        this.object = object;
    }

    public void run() {
        System.out.println("before unpark");
        // 释放许可
        LockSupport.unpark((Thread) object);
        System.out.println("after unpark");
    }
}