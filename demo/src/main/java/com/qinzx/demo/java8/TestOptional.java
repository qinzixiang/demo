package com.qinzx.demo.java8;

import java.util.Optional;

/**
 * @author qinzx
 * @date 2019/04/29 12:45
 */
public class TestOptional {
    public static void main(String[] args){
        System.out.println(Optional.ofNullable(new Person())
                .map(x->x.country)
                .map(x->x.provinec)
                .map(x->x.city)
                .map(x->x.name)
                .orElse("unkonwn"));
        System.out.println(new Person().country.provinec.city);
    }
}
class Person {
    Country country;
}
class Country {
    Province provinec;
}
class Province {
    City city;
}
class City {
    String name;
}