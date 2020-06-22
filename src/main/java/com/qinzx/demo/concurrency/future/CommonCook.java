package com.qinzx.demo.concurrency.future;

/**
 * 假如你突然想做饭，但是没有厨具，也没有食材。网上购买厨具比较方便，食材去超市买更放心。
 *
 * 通过分析代码：
 * 可以看到，多线程已经失去了意义。在厨具送到期间，我们不能干任何事。对应代码，就是调用join方法阻塞主线程。
 * 有人问了，不阻塞主线程行不行？？？
 * 不行！！！
 *
 * 从代码来看的话，run方法不执行完，属性chuju就没有被赋值，还是null。换句话说，没有厨具，怎么做饭。
 *
 * Java现在的多线程机制，核心方法run是没有返回值的；如果要保存run方法里面的计算结果，必须等待run方法计算完，无论计算过程多么耗时。
 *
 * 面对这种尴尬的处境，程序员就会想：在子线程run方法计算的期间，能不能在主线程里面继续异步执行？？？
 *
 * Where there is a will，there is a way！！！
 *
 * 这种想法的核心就是Future模式，下面先应用一下Java自己实现的Future模式。
 */
public class CommonCook {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        //第一步   网购厨具
        OnlineShopping onlineShopping = new OnlineShopping();
        onlineShopping.start();
        onlineShopping.join();  //保证厨具送到
        //第二步   去超市购买食材
        Thread.sleep(2000);
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        //第三步   用厨具烹饪食材
        System.out.println("第三步：开始展现厨艺");
        cook(onlineShopping.chuju, shicai);
        System.out.println("总共用时：" + (System.currentTimeMillis() - startTime));
    }

    static class OnlineShopping extends Thread {
        private Chuju chuju;

        @Override
        public void run() {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步：快递送到");
            chuju = new Chuju();
        }
    }

    static void cook(Chuju chuju, Shicai shicai) {
        System.out.println("做饭...");
    }
    static class Chuju {
    }

    static class Shicai {
    }

}
