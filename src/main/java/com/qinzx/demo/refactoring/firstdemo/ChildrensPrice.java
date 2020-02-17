package com.qinzx.demo.refactoring.firstdemo;

/**
 * 重构添加的类
 * @ClassName: ChildrensPrice
 * @Author qinzx
 * @Date 2019/12/02 14:02
 * @Copyright (C) 杭州同基汽车科技有限公司
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