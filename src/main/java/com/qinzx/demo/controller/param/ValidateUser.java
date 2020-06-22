package com.qinzx.demo.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * @author qinzx
 * @date 2020/06/22 11:16
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
@Data
public class ValidateUser {
    @Length(max = 5, min = 2)
    private String name;
    @Min(5)
    private Integer age;
}