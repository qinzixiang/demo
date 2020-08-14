package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @author qinzx
 * @date 2019/12/02 14:04
 */
public class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented){
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}