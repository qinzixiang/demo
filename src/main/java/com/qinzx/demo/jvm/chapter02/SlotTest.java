package com.qinzx.demo.jvm.chapter02;

/**
 * 演示局部变量表Slot复用对垃圾收集的影响
 * @author qinzx
 * @date 2020/02/27 16:32
 */
public class SlotTest {
    public static void main(String[] args){
        //第一种情况，此时不能回收内存，因为placeholder还在作用域中
//        byte[] placeholder = new byte[64 * 1024 * 1024];
//        System.gc();
        //第二种情况，placeholder不再作用域中了,但是还没有回收
//        {
//            byte[] placeholder = new byte[64 * 1024 * 1024];
//        }
//        System.gc();

        //第三种情况,回收成功，因为在第二种情况下，placeholder离开作用域后，没有
        //没有任何对局部变量表的读写操作，placeholder原本所占用的Slot还没有被其
        // 他变量所复用，所以作为GC Roots一部分的局部变量表仍然保持着对它的关联。
        // 这种关联没有被及时打断，绝大部分情况下影响都很轻微。但如果遇到一个方法
        // ，其后面的代码有一些耗时很长的操作，而前面又定义了占用了大量内存、实际
        // 上已经不会再被使用的变量，手动将其设置为null值就不是一个毫无意义的操作。
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}