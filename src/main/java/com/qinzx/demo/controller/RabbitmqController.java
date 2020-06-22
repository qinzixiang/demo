package com.qinzx.demo.controller;

import com.qinzx.demo.controller.param.ValidateParam;
import com.qinzx.demo.thirdjar.rabbitmq.Sender;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author qinzx
 * @date 2019/05/11 18:09
 */
//@Validated //这里加没有用，还是需要在需要验证的参数前增加注解
@RestController
@RequestMapping("/rabbit")
public class RabbitmqController {
    @Resource
    private Sender sender;

    @RequestMapping("/send")
    public String sendMsg() {
        sender.send();
        return "发送消息";
    }

//    @Validated    这里加没有用，要在参数前加
    @PostMapping("/validate")
    public String validate(@Validated @RequestBody ValidateParam param) {

        return "成功";
    }
}