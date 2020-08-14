package com.qinzx.demo.thirdjar.rabbitmq;

import com.qinzx.demo.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class SenderTest extends BaseTest {
    @Resource
    private Sender sender;

    @Test
    public void send() {
        sender.send();
    }
}