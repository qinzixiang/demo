package com.qinzx.demo.concurrency;

import lombok.Getter;

/**
 * @author : qinzx
 * @program : demo
 * @description :
 * @create : 2020-06-08 22:01
 **/
@Getter
public enum CountryEnum {
    ONE(1,"齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "韩"),
    FIVE(5, "赵"),
    SIX(6,"魏")
    ;
    private Integer code;
    private String value;

    CountryEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static CountryEnum getByCode(int code) {
        for (CountryEnum anEnum : values()) {
            if (code == anEnum.getCode()) {
                return anEnum;
            }
        }
        return null;
    }
}
