package com.qinzx.demo.java8;

import java.math.BigDecimal;

/**
 * @author qinzx
 * @date 2019/09/05 15:01
 */
public class BigDecimalCal {
    public static void main(String[] args){

        BigDecimal totalD = new BigDecimal(1999.56);
        BigDecimal divideD = new BigDecimal(6).divide(new BigDecimal(1000));
        BigDecimal multiplyD = totalD.multiply(divideD);
        System.out.println(totalD.subtract(multiplyD));
        System.out.println(multiplyD);
        System.out.println(divideD);
        //使用字符串构造函数，不会出现精度损失
        BigDecimal total = new BigDecimal("1999.56");
        BigDecimal divide = new BigDecimal(6).divide(new BigDecimal(1000));
        BigDecimal multiply = total.multiply(divide);
        System.out.println(total.subtract(multiply));
        System.out.println(multiply);
        System.out.println(divide);
    }
}