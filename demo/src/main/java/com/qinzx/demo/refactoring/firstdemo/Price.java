package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @author qinzx
 * @date 2019/12/02 14:01
 */
public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}