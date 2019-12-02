package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @ClassName: Price
 * @Author qinzx
 * @Date 2019/12/02 14:01
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}