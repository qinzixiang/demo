package com.qinzx.demo.jvm.chapter05;

/**
 * @author : qinzx
 * @create : 2020-02-16 20:47
 */
public class OperandStackTest {
    public void testAddOperation() {
        byte i = 15;
        int j = 8;
        int k = i + j;

        int m = 8;//8为byte范围内，则字节码指令还是使用bipush
    }

    public int getSum() {
        int m = 10;
        int n = 20;
        int k = m + n;
        return k;
    }

    public void testGetSum() {
        int i = getSum();
        int j = 10;
    }

    /**
     * i++ 和 ++i 的区别
     */
    public void add() {
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        int i9 = 10;
        int i10 = i9++ + ++i9;
    }
}
