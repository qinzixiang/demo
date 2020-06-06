package com.qinzx.demo.jvm.part5.thisescape;

/**
 * 嵌套构造器调用会在编译期进行合并
 * @author qinzx
 * @date 2020/03/27 10:42
 */
public class Test {
    public static void main(String[] args){

    }
}
class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Student extends Person {
    private String school;

    Student(String school) {
        this("George", 10, school);
    }

    Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }
}