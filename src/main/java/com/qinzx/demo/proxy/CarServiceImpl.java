package com.qinzx.demo.proxy;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qinzx
 * @date 2020/06/22 16:26
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
@Component
public class CarServiceImpl implements CarService {
    private AtomicInteger numOfPassengers = new AtomicInteger();

    @Override
    public void didi() {
        System.out.println("鸣笛！！！");
    }

    @Override
    public void stop() {
        System.out.println("停车！！！");
    }

    @Override
    public int addPeople() {
        System.out.println("上车一人");
        return numOfPassengers.incrementAndGet();
    }

    @Override
    public int decreasePeople() {
        System.out.println("下车一人");
        return numOfPassengers.decrementAndGet();
    }
}