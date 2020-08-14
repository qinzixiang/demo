package com.qinzx.demo.controller.param;

import com.qinzx.demo.kotlin.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author qinzx
 * @date 2020/06/22 10:46
 */
@Data
public class ValidateParam {
    @Length(max = 8,min = 5)
    private String name;
    @Max(9)
    private Integer age;
    @Valid
    @NotNull
    private List<User> list;
    @Valid
    @NotNull
    private List<ValidateUser> list1;
}