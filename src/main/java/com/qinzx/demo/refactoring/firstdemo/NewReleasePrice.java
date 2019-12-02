package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @ClassName: NewReleasePrice
 * @Author qinzx
 * @Date 2019/12/02 14:03
 * @Copyright (C) 杭州同基汽车科技有限公司
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