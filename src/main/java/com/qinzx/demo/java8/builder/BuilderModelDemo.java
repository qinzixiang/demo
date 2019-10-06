package com.qinzx.demo.java8.builder;

/**
 * builder模式实现，builder模式结合了 重叠构造器模式和javaBean模式的优点，安全且便于使用。
 * 原始的实现方式，需要编写很多的重复代码，要实现自有的静态内部类，使用lombok工具包的@Builder注解可以
 * 省略很多重复代码，@Singular注解可以方便的为集合属性（list，map，set等）赋值
 * java8中可以构建通用Builder，以减少重复代码，也不要引入lombok或类似的第三方包
 *
 * 使用Lambda优化日志案例:
 *     Lambda的特点:延迟加载
 *     Lambda的使用前提,必须存在函数式接口
 */
public class BuilderModelDemo {
    //定义一个显示日志的方法,方法的参数传递日志的等级和MessageBuilder接口
    public static void showLog(int level, MessageBuilder mb){
        //对日志的等级进行判断,如果是1级,则调用MessageBuilder接口中的builderMessage方法
        if(level==1){
            System.out.println(mb.builderMessage());
        }
    }
    public static void main(String[] args) {
        //定义三个日志信息
        String msg1 = "Hello";
        String msg2 = "World";
        String msg3 = "Java";
        //调用showLog方法,参数MessageBuilder是一个函数式接口,所以可以传递Lambda表达式
        showLog(2,()->{
            //返回一个拼接好的字符串
            return  msg1+msg2+msg3;
        });
        /*
            使用Lambda表达式作为参数传递,仅仅是把参数传递到showLog方法中
            只有满足条件,日志的等级是1级
                才会调用接口MessageBuilder中的方法builderMessage
                才会进行字符串的拼接
            如果条件不满足,日志的等级不是1级
                那么MessageBuilder接口中的方法builderMessage也不会执行
                所以拼接字符串的代码也不会执行
            所以不会存在性能的浪费
         */

//        showLog(1,() -> {
                // 先传入1，判断好了再拼接输出， 而不是拼接好后，判断，再输出。
                //     System.out.println("不满足条件不执行");
                // 重写了builderMessage函数
                //返回一个拼接好的字符串
//            return  msg1+msg2+msg3;
//        });
        showLog(1,() -> msg1+msg2+msg3);
    }
}
