package com.qinzx.demo.jvm.chapter02;

/**
 * 单分派、多分派
 *
 * @author qinzx
 * @date 2020/02/29 9:43
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class Dispatch {
    static class QQ{
    }
    static class _360{
    }
    public static class Father{
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father{

        @Override
        public void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }

        @Override
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }
    public static void main(String[] args){
        Father father = new Father();
        Father son = new Son();
        /**
         * 编译阶段的选择过程：即静态分派的过程。这时候选择目标方法的依据有两点：
         * 一是静态类型是Father还是Son，二是方法参数是QQ还是360.最终产生了两条
         * invokevirtual指令，两个指令的参数分别为常量池中执行Father.hardChoice(360)
         * 及Father.hardChoice(QQ)方法的符号引用。因为是根据两个宗量进行选择，
         * 所以Java语言的静态分派属于多分派类型。
         * 再看看运行阶段虚拟机的选择，即动态分派的过程。在执行“son.hardChoice(new QQ())”
         * 这句代码时，更准确地说，在执行这句代码对应的invokevirtual指令时，由
         * 于编译期已经决定目标方法的签名必须为hardChoice(QQ)，虚拟机此时不会关
         * 心传递过来的参数“QQ”到底是“腾讯QQ”还是“奇瑞QQ”，因为这时候参数的静态
         * 类型、实际类型都不会对方法的选择构成任何影响，唯一可以相应虚拟机选择的
         * 因素只有此方法的接收者的实际类型是Father还是Son。因为只有一个宗量作为
         * 选择依据，所以Java语言的动态分派属于单分派类型。
         */
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}