package com.qinzx.demo.jvm.chapter02;

/**
 * 初始化阶段就是执行类构造器方法<clinit>()的过程
 * 此方法不需定义，是javac编译器自动收集类中的所有类变量的赋值动作
 * 和静态代码块中的语句合并而来。
 * 构造器方法中指令按语句在源文件中出现的顺序执行。
 * 子类的clinit方法会在父类的clinit方法执行之后执行
 * @author qinzx
 * @date 2020/02/25 16:34
 */
public class ClinitTest {
    static class Father {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Son extends Father {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Son.B);
    }
}