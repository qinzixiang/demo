package com.qinzx.demo.concurrency;

import java.io.*;
import java.util.ListIterator;

/**
 * Java语言的关键字，变量修饰符，如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。
 * 换句话来说就是，用transient关键字标记的成员变量不参与序列化过程。
 *
 * 作用：Java的serialization提供了一种持久化对象实例的机制。当持久化对象时，可能有一个特殊的对象数据成员，
 * 我们不想用serialization机制来保存它。为了在一个特定对象的一个域上关闭serialization，可以在这个域前加
 * 上关键字transient。当一个对象被序列化的时候，transient型变量的值不包括在序列化的表示中，
 * 然而非transient型的变量是被包括进去的。
 *
 * @author qinzx
 * @date 2019/07/09 17:49
 */
public class TransientTest implements Serializable {
    private static final long serialVersionUID = 233858934995755239L;
    private String name1;
    private transient String name2;

    public TransientTest(String name1,String name2){
        this.name1 = name1;
        this.name2 = name2;
    }

    @Override
    public String toString(){
        return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
    }

    public static void main(String[] args){
        testTransient();

    }

    public static void testTransient(){
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest test = new TransientTest(name1, name2);
        System.out.println("序列化前："+test.toString());
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        String filePath = "e:/TransientTest.obj";
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(test);

            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest readObject = (TransientTest)inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 延伸：
     *         在查看JDK源码的时候会发现很多地方都会加上transient关键字来修饰一些属性，那究竟是出于什么考虑才这么做呢？
     *         我觉得，应该是为了节约磁盘空间，避免造成不必要的浪费吧。
     *         以ArrayList中的 transient Object[] elementData 为例，这个成员变量的注释为：
     * @see java.util.ArrayList#elementData
     * 注释翻译过来
     * / * *
     * *存储ArrayList元素的数组缓冲区。
     * * ArrayList的容量是这个数组缓冲区的长度。任何
     * *带有elementData的空ArrayList == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * *当添加第一个元素时，将被扩展到DEFAULT_CAPACITY。
     * * /
     * 这个缓冲区的容量实际上并不是ArrayList的容量，因为其实际上会预留一些空间，当空间不足时还会扩容，
     * 为减少浪费，因此在序列化时不会按照默认算法将这个成员变量写入磁盘。而是写了个writeObject方法，
     * 序列化时会调用这个方法将其持久化，在反序列化是，调用readObject，将其恢复出来。
     * 这2个方法为：
     *         {@link java.util.ArrayList#writeObject(ObjectOutputStream)}
     *         {@link java.util.ArrayList#readObject(ObjectInputStream)}
     * 参考ArrayList，在上面的TransientTest中添加2个方法，见代码：
     */
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();
        s.writeObject(name2);
    }
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        name2=String.valueOf(s.readObject());
    }
}