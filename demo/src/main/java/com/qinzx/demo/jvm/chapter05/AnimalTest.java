package com.qinzx.demo.jvm.chapter05;

/**
 * 方法的绑定机制：早期绑定（静态绑定）和晚期绑定（动态绑定）
 * @author : qinzx
 * @create : 2020-02-16 22:16
 **/
public class AnimalTest {
    public void showAnimal(Animal animal) {
        animal.eat();   //晚期绑定
    }
    public void showHunt(Huntable huntable) {
        huntable.hunt();    //晚期绑定
    }
}

class Animal{
    public void eat() {
        System.out.println("动物进食");
    }
}

interface Huntable{
    void hunt();
}
class Dog extends Animal implements Huntable{
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void hunt() {
        System.out.println("狗拿耗子，多管闲事");
    }
}
class Cat extends Animal implements Huntable{
    public Cat() {
        super();//早期绑定
    }

    public Cat(String name) {
        this();//早期绑定
    }
    @Override
    public void eat() {
        super.eat();
        System.out.println("猫吃鱼");
    }

    @Override
    public void hunt() {
        System.out.println("猫捉老鼠，天经地义");
    }
}