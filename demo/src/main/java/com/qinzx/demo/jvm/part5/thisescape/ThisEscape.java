package com.qinzx.demo.jvm.part5.thisescape;


/**
 * this逃逸示例
 *
 * @author qinzx
 * @date 2020/03/27 10:04
 */
public class ThisEscape {
    public final int id;
    public final String name;
    public ThisEscape(EventSource<EventListener> source) {
        id = 1;
        source.registerListener(new EventListener() {  //内部类是可以直接访问外部类的成员变量的（外部类引用this被内部类获取了）
            @Override
            public void onEvent(Object obj) {
                System.out.println("id: "+id);
                System.out.println("name: "+ThisEscape.this.name);
            }
        });
        name = "flysqrlboy";
    }
}