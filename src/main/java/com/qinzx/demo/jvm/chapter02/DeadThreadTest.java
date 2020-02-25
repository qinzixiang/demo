package com.qinzx.demo.jvm.chapter02;

/**
 * 虚拟机必须保证一个类的<clinit>方法在多线程下被同步加锁
 * @ClassName: DeadThreadTest
 * @author qinzx
 * @date 2020/02/25 16:28
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class DeadThreadTest {
    public static void main(String[] args){
        Runnable r = () ->{
            System.out.println(Thread.currentThread().getName()+"开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName()+"结束");
        };
        Thread t1 = new Thread(r, "线程1");
        Thread t2 = new Thread(r, "线程2");
    }
}
class DeadThread{
    static{
        if (true) {
            System.out.println(Thread.currentThread().getName()+"初始化当前类");
            while (true) {

            }
        }
    }
}