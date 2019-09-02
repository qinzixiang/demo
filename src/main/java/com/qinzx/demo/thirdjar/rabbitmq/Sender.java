package com.qinzx.demo.thirdjar.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @ClassName: Sender
 * @Author qinzx
 * @Date 2019/05/07 11:23
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String msg = "sender send..." + LocalDate.now();
        amqpTemplate.convertAndSend("myQueue", msg);
    }

}