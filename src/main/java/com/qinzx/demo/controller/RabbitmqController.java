package com.qinzx.demo.controller;

import com.qinzx.demo.thirdjar.rabbitmq.Sender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinzx
 * @date 2019/05/11 18:09
 */
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
}