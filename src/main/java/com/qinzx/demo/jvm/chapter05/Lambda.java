package com.qinzx.demo.jvm.chapter05;

/**
 *
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-02-17 19:32
 **/
interface Func{
    public boolean func(String string);
}
public class Lambda {
    public void lambda(Func func) {
        return;
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        Func func = s -> {
            return true;
        };
        lambda.lambda(func);
        lambda.lambda(s -> {
            return true;
        });
    }
}
