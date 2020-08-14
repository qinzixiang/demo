package com.qinzx.demo.jvm.chapter05;

/**
 * 解析调用中非虚方法、虚方法的测试
 *
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-02-17 10:19
 **/
public class Son extends Father {
    public Son() {
        super();
    }

    public Son(int age) {
        this();
    }

    //不是重写的，静态方法不能重写
    public static void showStatic(String string) {
        System.out.println("son" + string);
    }

    public void showPrivate(String string) {
        System.out.println("son private" + string);
    }

    public void show() {
        showStatic("qinzx");
        super.showStatic("good");
        showPrivate("hello");
        super.showCommon();
        showFinal();
        showCommon();
        info();

        MethodInterface in = null;
        in.methodA();
    }


    private void info() {
    }
}

interface MethodInterface {
    void methodA();
}

class Father {
    public Father() {
        System.out.println("father的构造器");
    }

    public static void showStatic(String string) {
        System.out.println("father " + string);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}
