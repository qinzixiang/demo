package com.qinzx.demo.concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join框架
 * 类似MapReduce的设计思想，将一个大任务分成若干个小人物，使用多线程处理，最后的结果汇总在一起
 * Linux平台中，Fork()函数用来创建子进程，使得系统进程可以多一个执行分支，java中沿用了类似的命名方式
 * join()表示等待，也就是使用fork()后系统多了一个执行分支（线程），所以需要等待这个执行分支执行完毕，
 * 才有可能得到最终的结果，因此join()就表示等待。
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //分成100个小任务
            long step = (start+end)/100;
            ArrayList<ForkJoinDemo> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                ForkJoinDemo subTask = new ForkJoinDemo(pos, lastOne);
                pos += step + 1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (ForkJoinDemo task : subTasks) {
                sum += task.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0, 200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            long l = System.currentTimeMillis() - start;
            System.out.println("sum=" + res+ " "+l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long ss = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i <= 200000; i++) {
            sum += i;
        }
        long l = System.currentTimeMillis() - ss;
        System.out.println(sum+ " "+l);
    }
}
