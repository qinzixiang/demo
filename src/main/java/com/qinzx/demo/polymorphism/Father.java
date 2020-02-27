package com.qinzx.demo.polymorphism;

/**
 * 覆写的规则，一大（执行权限），两小（返回值类型、抛出异常的类型都要是父类的子类型），两同（方法名、参数类型和个数）
 * @author qinzx
 * @date 2019/04/29 9:52
 */
public class Father {
    protected void doSomething() {
        System.out.println("Father's do something");
        this.doSomething();
    }
    public static void main(String[] args){
        Father father = new Son();
        father.doSomething();
    }
}

class Son extends Father{
    @Override
    public void doSomething() {
        System.out.println("Son's do something");
        super.doSomething();
    }
}