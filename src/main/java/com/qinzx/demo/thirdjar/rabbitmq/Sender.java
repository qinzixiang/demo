package com.qinzx.demo.thirdjar.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @ClassName: Sender
 * @Author qinzx
 * @Date 2019/05/07 11:23
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
@Component
public class Sender {
    @Resource
    private AmqpTemplate amqpTemplate;

    public void send() {
        String msg = "sender send..." + LocalDate.now();
        amqpTemplate.convertAndSend("hello-test", msg);
    }

}