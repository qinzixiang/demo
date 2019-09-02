package com.qinzx.demo.concurrency;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ProducerConsumerTest
 * @Author qinzx
 * @Date 2019/07/13 14:04
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class ProducerConsumerTest {

    private Lock lock = new ReentrantLock();

    private Condition addCondition = lock.newCondition();

    private Condition removeCondition = lock.newCondition();

    private LinkedList<Integer> resources = new LinkedList<>();

    private int maxSize;

    public ProducerConsumerTest(int maxSize) {
        this.maxSize = maxSize;
    }


    public class Producer implements Runnable {

        private int proSize;

        private Producer(int proSize) {
            this.proSize = proSize;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println("生产者尝试生成。。");
                    for (int i = 1; i <= proSize; i++) {
                        while (resources.size() >= maxSize) {
                            System.out.println("当前仓库已满，等待消费...");
                            try {
                                addCondition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        resources.add(i);
                        System.out.println("已经生产产品数: " + i + "\t现仓储量总量:" + resources.size());
                        removeCondition.signal();
                    }
                } finally {
                    System.out.println("生产者解锁");
                    lock.unlock();
                }
            }
        }
    }

    public class Consumer implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (true) {
                lock.lock();
                try {
                    System.out.println("消费者尝试中。。");
                    while (resources.size() <= 0) {
                        System.out.println(threadName + " 当前仓库没有产品，请稍等...");
                        try {
                            // 进入阻塞状态
                            removeCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 消费数据
                    int size = resources.size();
                    for (int i = 0; i < size; i++) {
                        Integer remove = resources.remove();
                        System.out.println(threadName + " 当前消费产品编号为：" + remove);
                    }
                    // 唤醒生产者
                    addCondition.signal();
                } finally {
                    System.out.println("消费者解锁");
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerTest producerConsumerTest = new ProducerConsumerTest(10);
        Producer producer = producerConsumerTest.new Producer(100);
        Consumer consumer = producerConsumerTest.new Consumer();
        final Thread producerThread = new Thread(producer, "producer");
        final Thread consumerThread = new Thread(consumer, "consumer");
        producerThread.start();
        TimeUnit.SECONDS.sleep(2);
        consumerThread.start();
    }
}
