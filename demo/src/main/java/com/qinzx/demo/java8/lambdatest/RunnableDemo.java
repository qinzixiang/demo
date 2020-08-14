package com.qinzx.demo.java8.lambdatest;

/**
 * 函数式接口的用法
 * 如果抛开实现原理不说，Java中的Lambda表达式可以被当作是匿名内部类的替代品。如果方法的参数是一个函数 式接口类型，
 * 那么就可以使用Lambda表达式进行替代。使用Lambda表达式作为方法参数，其实就是使用函数式 接口作为方法参数。
 * 例如 java.lang.Runnable 接口就是一个函数式接口，假设有一个 startThread 方法使用该接口作为参数，那么就
 * 可以使用Lambda进行传参。这种情况其实和 Thread 类的构造方法参数为 Runnable 没有本质区别。
 *
 * Runnable接口就是一个函数式接口，作为参数传递
 */
public class RunnableDemo {
    //定义一个方法startThread,方法的参数使用函数式接口Runnable
    public static void startThread(Runnable run) {
        //开启多线程
        new Thread(run).start();
    }

    public static void main(String[] args) {
        //调用startThread方法,方法的参数是一个接口,那么我们可以传递这个接口的匿名内部类
        startThread(new Runnable() {    // new Runnable 函数接口作为参数
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
            }
        });
        //调用startThread方法,方法的参数是一个函数式接口,所以可以传递Lambda表达式
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
        };
//        startThread(runnable);
        startThread(() -> {
            System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
        });

        //优化Lambda表达式
        Runnable runnable1 = () -> System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
        startThread(runnable1);
    }
}
