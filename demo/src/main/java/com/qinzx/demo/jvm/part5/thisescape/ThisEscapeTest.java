package com.qinzx.demo.jvm.part5.thisescape;


/**
 * 导致this引用逃逸的原因有两个：
 * 一个是在构造函数中创建内部类(EventListener)，
 * 另一个是在构造函数中就把这个内部类给发布了出去(source.registerListener)。
 * 同时满足极有可能发生逃逸
 *
 * @author qinzx
 * @date 2020/03/27 10:08
 */
public class ThisEscapeTest {
    public static void main(String[] args) {
        EventSource<EventListener> source = new EventSource<EventListener>();
        ListenerRunnable listRun = new ListenerRunnable(source);
        Thread thread = new Thread(listRun);
        thread.start();
        ThisEscape escape1 = new ThisEscape(source);
    }
}