package com.qinzx.demo.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ParamDemo
 * @Author qinzx
 * @Date 2019/06/05 16:49
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class ParamDemo {

    class Boy{
        String name;
        Integer age;
        List<String> list;

        public Boy() {
            this.name = "tom";
            this.age = 23;
            list = new ArrayList<>();
            list.add("cn");
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Boy{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", list=" + list +
                    '}';
        }
    }

    private static int intstatic = 222;
    private static String stringStatic = "old string";
    private static StringBuilder stringBuilderStatic = new StringBuilder("old stringBuilder");
    public static void method() {
        intstatic = 888;
    }

    public static void method(int intstatic) {
        intstatic = 777;
    }

    public static void method(String stringStatic) {
        stringStatic = "new String";
    }
    public static void method(Boy boy) {
        boy.setAge(11);
        boy.name = "中国";
        boy.list.add("us");
    }
    public static void method(StringBuilder stringBuilderStatic1, StringBuilder stringBuilderStatic2) {
        stringBuilderStatic1.append(".method.first-");
        stringBuilderStatic2.append(".method.second-");

        stringBuilderStatic1 = new StringBuilder("new stringBuilder");
        stringBuilderStatic1.append("new method's append");
    }
    public static void main(String[] args){
        //实参调用
        method(intstatic);
        method(stringStatic);
        method(stringBuilderStatic, stringBuilderStatic);

        System.out.println(intstatic);
        method();
        System.out.println(intstatic);
        System.out.println(stringStatic);
        System.out.println(stringBuilderStatic);
        ParamDemo paramDemo = new ParamDemo();
        Boy test = paramDemo.test();
        method(test);
        System.out.println(test);
    }

    public Boy test() {
        return new Boy();
    }
}