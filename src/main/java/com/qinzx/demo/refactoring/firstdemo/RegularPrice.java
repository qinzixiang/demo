package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @ClassName: RegularPrice
 * @Author qinzx
 * @Date 2019/12/02 14:04
 * @Copyright (C) 杭州同基汽车科技有限公司
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