package com.qinzx.demo.concurrency.lock;

import java.util.concurrent.TimeUnit;

/**
 * 重入锁：ReentrantLocak/synchronized都支持重入锁（即递归锁）
 * 一个锁同步的方法可以调用另一个锁同步的方法：当一个线程获取一个方法的锁后
 * 会自动获取此方法中所有的带锁的方法的锁（包括方法中调用的方法）。
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-06-06 10:03
 **/
public class ReentrantLockDemo3 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}

class Phone{


    public synchronized void sendSms()throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t sendSms() come in");
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSms()");
        sendEmail();
    }

    public synchronized void sendEmail()throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t #### sendEmail() come in");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"\t #### invoked sendEmail()");
    }

}