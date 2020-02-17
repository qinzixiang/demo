package com.qinzx.demo.extend;

import lombok.Data;

/**
 * @ClassName: SuperDemo
 * @Author qinzx
 * @Date 2019/11/12 10:39
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
@Data
public class SuperDemo {
    private Integer a = 0;
    protected Integer b = 1;
    Integer c = 2;
}