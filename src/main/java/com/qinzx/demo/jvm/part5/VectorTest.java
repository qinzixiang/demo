package com.qinzx.demo.jvm.part5;

import java.util.Vector;

/**
 * 绝对线程安全：Vector是相对线程安全 ，但不是绝对安全，
 * Java中，大部分的线程安全类都属于相对线程安全，例如：Vector、HashTable、Collections的synchronizedCollection()方法包装的集合等。
 *
 * 虽然Vector的get(),remove(),size()方法都是同步的，但是在多线程环境中，如果不在方法调用端做额外的同步措施，使用这段代码仍然是不安全的，
 * 因为如果另一个线程恰好在错误的时间里删除了一个元素，导致序号i已经不再可用的话，get()方法就会抛出一个ArrayIndexOutOfBoundsException.
 * 如果要保证这段代码能正确的执行下去，需要使用synchronized
 *
 * 运行错误：
 * Exception in thread "Thread-30345505" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 8
 * 	at java.util.Vector.get(Vector.java:751)
 * 	at com.qinzx.demo.jvm.part5.VectorTest.lambda$main$1(VectorTest.java:25)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * @author qinzx
 * @date 2020/03/27 13:39
 */
public class VectorTest {
    private static Vector<Integer> vector = new Vector<>();
    public static void main(String[] args){
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removeThread = new Thread(() -> {
//                synchronized (vector) {  加上这行才能保证对vector的操作是线程同步的
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
//                }
            });

            Thread printThread = new Thread(() -> {
//                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
//                }
            });

            removeThread.start();
            printThread.start();

            //不要产生过多的线程，否则会导致操作系统假死
            while (Thread.activeCount() > 20) {

            }
        }
    }
}