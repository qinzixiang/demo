package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @author qinzx
 * @date 2019/12/02 14:02
 */
public class ChildrensPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented){
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}