package com.qinzx.demo.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 演示spring aop环绕切面
 * @author qinzx
 * @date 2020/06/22 17:34
 */
@RestController
@RequestMapping("/aop")
public class SpringAopDemo {

    @Resource
    private CarService carService;

    @GetMapping("add")
    public int add() {
        return carService.addPeople();
    }

    @GetMapping("decrease")
    public int decrease() {
        return carService.decreasePeople();
    }
}