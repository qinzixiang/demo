package com.qinzx.demo.thirdjar.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qinzx
 * @date 2019/05/07 11:32
 */
@Slf4j
@Component
public class Receiver {

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void receive(String msg) {
        log.info("mqReceive = {}", msg);
        System.out.println(msg);
    }
}