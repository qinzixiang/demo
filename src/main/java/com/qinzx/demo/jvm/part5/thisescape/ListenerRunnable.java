package com.qinzx.demo.jvm.part5.thisescape;

import java.util.List;

/**
 * @author qinzx
 * @date 2020/03/27 10:07
 */
public class ListenerRunnable implements Runnable {
    private EventSource<EventListener> source;
    public ListenerRunnable(EventSource<EventListener> source) {
        this.source = source;
    }

    @Override
    public void run() {
        List<EventListener> listeners = null;

        try {
            listeners = this.source.retrieveListeners();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(EventListener listener : listeners) {
            listener.onEvent(new Object());  //执行内部类获取外部类的成员变量的方法
        }
    }
}