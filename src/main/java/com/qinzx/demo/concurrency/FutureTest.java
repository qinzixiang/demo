package com.qinzx.demo.concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 使用future和callable
 * @author qinzx
 * @date 2019/07/11 10:35
 */
public class FutureTest {
    public void chandeValue(String string) {
        string = "ddd";
    }
    public static void main(String[] args){
        FutureTest futureTest = new FutureTest();
        String a = "123";
        String b = new String("33d");
        futureTest.chandeValue(a);
        futureTest.chandeValue(b);
        System.out.println(a);
        System.out.println(b);
        /*ExecutorService executorService = Executors.newCachedThreadPool();
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
        }*/
    }
}