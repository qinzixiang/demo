package com.qinzx.demo.thirdjar.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Receiver
 * @Author qinzx
 * @Date 2019/05/07 11:32
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
@Slf4j
@Component
public class Receiver {

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void receive(String msg) {
        log.info("mqReceive = {}", msg);
    }
}