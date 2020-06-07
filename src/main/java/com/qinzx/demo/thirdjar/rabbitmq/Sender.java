package com.qinzx.demo.thirdjar.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author qinzx
 * @date 2019/05/07 11:23
 */
@Component
public class Sender {
    @Resource
    private AmqpTemplate amqpTemplate;

    public void send() {
        String msg = "sender send..." + LocalDate.now();
        amqpTemplate.convertAndSend("myQueue", msg);
    }

}