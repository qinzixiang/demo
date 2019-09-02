package com.qinzx.demo;

import com.qinzx.demo.thirdjar.rabbitmq.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
/**
 *
 * @author  qinzx
 * @date  2019/5/11 15:30
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if (next.equals("send")) {
            new Sender().send();
        }
        System.out.println("消息发送成功");

    }

}
