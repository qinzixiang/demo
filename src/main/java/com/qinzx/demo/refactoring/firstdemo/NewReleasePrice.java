package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @author qinzx
 * @date 2019/12/02 14:03
 */
public class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented){
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented){
        return (daysRented > 1) ? 2 : 1;
    }
}