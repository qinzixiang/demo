package com.qinzx.demo.concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 使用future和callable
 * @author qinzx
 * @date 2019/07/11 10:35
 */
public class FutureTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        executorService.shutdown();

        try {
            System.out.println("result:" + result.get());
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}