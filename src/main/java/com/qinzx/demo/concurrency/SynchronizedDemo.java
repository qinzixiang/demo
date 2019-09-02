package com.qinzx.demo.concurrency;

/**
 * synchronized保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性。
 * 当多个并发线程访问同一个对象object中的同步代码块时，一个时间内只有一个线程能够得到执行，另一个线程必须等到当前线程执
 * 行完这个代码块之后才能执行，但是其他的线程仍然能够访问该object中的非synchronized同步代码块。
 *
 * java中每一个对象都可以作为锁，这是synchronized实现同步的基础：
 *
 * 1.修饰实例方法：锁是当前实例对象
 * 2.修饰静态方法：锁是当前类的class对象
 * 3.修饰代码块：锁是括号里的对象
 *
 * 1.修饰实例方法
 * 在下面的例子中定义了一个静态变量num，定义了一个incr()方法对num进行+1的操作，在run方法中是循环调用incr方法5次。
 * 最后在main方法中是创建了两个线程对num进行操作。在该方法中synchronized修饰的是实例方法incr，
 * 此时线程的锁便是实例对象instance。
 *
 * 一个对象只有一把锁，当一个线程获取了该对象的锁之后，其他的线程就无法获取该对象的锁，
 * 但是其他的对象还是可以访问该实例对象的其他非synchronized方法。
 *
 * @ClassName: SynchronizedDemo
 * @Author qinzx
 * @Date 2019/07/09 15:15
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class SynchronizedDemo implements Runnable{
    public static int num = 0;

    public synchronized void incr() {
        num++;
        System.out.println("Thread-Id:"+Thread.currentThread().getId()+" num="+num);
    }

    @Override
    public void run() {
        for(int i = 0; i< 5; i++) {
            incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo test1 = new SynchronizedDemo();
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test1);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num="+num);
        /*num=10*/

        /*
        在上面的例子中是两个线程共同操作一个对象，在下面的例子中可以看到两个线程操作的是不同的对象，
        这时候不同的对象有不同的锁，所以都会获取到各自的锁，这时候获取到的结果就不一定是10了，
        也许是比10小的数。这种情况下是不满足线程安全的。
         */
        /*Test1 test1 = new Test1();
        Test1 test2 = new Test1();
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test2);

        t1.start();
        t2.start();*/
        //System.out.println("num="+num);   /**num=10？？*/

    }

}