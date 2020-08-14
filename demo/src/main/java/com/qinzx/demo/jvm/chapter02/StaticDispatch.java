package com.qinzx.demo.jvm.chapter02;

/**
 * 方法调用：分派示例
 *
 * @author qinzx
 * @date 2020/02/27 21:04
 */
public class StaticDispatch {
    static abstract class Human{
    }
    static class Man extends Human{
    }
    static class Women extends Human{
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Women guy) {
        System.out.println("hello,lady!");
    }
    public static void main(String[] args){
        /**
         * “Human”为静态类型(Static Type)或者外观类型(Apparent Type)
         * “Man”则称为实际类型(Actual Type)
         * 静态类型和实际类型在程序中都可以发生一些变化，区别是静态类型的变化仅仅在使用时发生，
         * 变量本身的静态类型不会被改变，并且最终的静态类型是在编译期可知的；而实际类型变化的
         * 结果在运行期才可确定，编译器在编译程序的时候并不知道一个对象的实际类型是什么。如：
         *
         * 实际类型变化：
         * Human man = new Man();
         * man = new Women();
         * 静态类型变化：
         * sd.sayHello((Man) man)
         * sd.sayHello((Women) man)
         */
        Human man = new Man();
        Human women = new Women();
        StaticDispatch sd = new StaticDispatch();
        /**
         * 上面定义了两个静态类型相同、实际类型不同的变量，但虚拟机（准确地说是编译器）在重载时是
         * 通过参数的静态类型而不是实际类型作为判定依据的。并且静态类型是编译期可知的，所以在编译
         * 阶段，Javac编译器就根据参数的静态类型决定使用哪个重载版本，所以选择sayHello(Human)作
         * 为调用目标，并把这个方法的符号引用写到main()方法里的两条invokevirtual指令的参数中。
         *
         * 20 invokespecial #12 <com/qinzx/demo/jvm/chapter02/StaticDispatch.<init>>
         * 23 astore_3
         * 24 aload_3
         * 25 aload_1
         * 26 invokevirtual #13 <com/qinzx/demo/jvm/chapter02/StaticDispatch.sayHello>
         * 29 aload_3
         * 30 aload_2
         * 31 invokevirtual #13 <com/qinzx/demo/jvm/chapter02/StaticDispatch.sayHello>
         *
         * 所有依赖静态类型来定位方法执行版本的分派动作，都称为静态分派。静态分派的最典型应用就是
         * 方法重载。静态分派发生在编译阶段，因此确定静态分派的动作实际上不是由虚拟机来执行的。另外，
         * 编译器虽然能确定出方法的重载版本，但在很多情况下这个重载版本并不是“唯一的”，往往只能确定
         * 一个“更加适合的”版本。产生这种模糊结论的主要原因是字面量不需要定义，所以字面量没有显示的
         * 静态类型，它的静态类型只能通过语言上的规则去理解和推断。
         */
        sd.sayHello(man);
        sd.sayHello(women);
    }
}