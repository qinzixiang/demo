package com.global.lock1.demo;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootApplication
@RestController
public class DemoApplication {
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/lock1")
    public String lock1() {
        RLock lock = redissonClient.getLock("lock");
        lock.lock();
        try {
            System.out.println(new Date() + "lock1拿到锁");
            Thread.sleep(10000);
            new Test().test();
            return "lock1拿到锁";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "lock1拿到锁";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
