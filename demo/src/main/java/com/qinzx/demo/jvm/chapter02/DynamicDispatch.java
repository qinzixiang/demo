package com.qinzx.demo.jvm.chapter02;

/**
 * 动态分派示例：重写
 *
 * @author qinzx
 * @date 2020/02/28 15:10
 */
public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Women extends Human {
        @Override
        protected void sayHello() {
            System.out.println("women say hello");
        }
    }
    public static void main(String[] args){
        Human man = new Man();
        Human women = new Women();
        /**
         * 输出：
         * man say hello
         * women say hello
         * women say hello
         * 三个调用sayHello()方法的代码编译后的字节码中调用的都是Human.sayHello()符号引用
         * 21 invokevirtual #6 <com/qinzx/demo/jvm/chapter02/DynamicDispatch$Human.sayHello>
         * 但是这三条指令最终执行的目标方法并不相同，原因在于invokevirtual指令的多态查找过程。
         * invokevirtual指令的运行时解析过程大致分为以下步骤：
         * 1.找到操作数栈顶的第一个元素所指向的对象的实际类型，记作C
         * 2.如果在类型C中找到与常量中的描述符和简单名称都相符的方法，则进行访问权限校验，如果通过则
         * 返回这个方法的直接引用，查找过程结束；不通过则返回java.lang.IllegalAccessError异常
         * 3.否则，按照继承关系从下往上依次对C的各个父类进行第2步的搜索和验证过程
         * 4.如果始终没有找到合适的方法，则抛出java.lang.AbstractMethodError异常。
         * 由于invokevirtual指令执行的第一步就是在运行期确定接收者（调用方法的所有者）的实际类型，所
         * 以前两次调用解析到了不同直接引用上。这个过程就是Java语言中方法重写的本质。我们把这种在运行
         * 期根据实际类型确定方法执行版本的分派过程称为动态分派。
         */
        man.sayHello();
        women.sayHello();
        man = new Women();
        man.sayHello();
    }
}