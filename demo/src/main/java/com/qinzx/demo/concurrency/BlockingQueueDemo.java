package com.qinzx.demo.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-06-10 21:00
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
