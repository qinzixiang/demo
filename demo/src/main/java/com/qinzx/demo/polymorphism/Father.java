package com.qinzx.demo.polymorphism;

/**
 * 覆写的规则，一大（执行权限），两小（返回值类型、抛出异常的类型都要是父类的子类型），两同（方法名、参数类型和个数）
 * 示例展示了子类调用父类的方法,作者设计AbstractQueuedSynchronizer不可以直接使用，在需要子类重新实现的方法中直接抛出不支持的异常，
 * 这样一旦子类没有实现相应的方法，就会抛出异常；这样设计不会强制子类复写父类的方法，在未实现就调用时也会有相应的异常提示。
 *
 * @author qinzx
 * @date 2019/04/29 9:52
 */
public class Father {
    protected void doSomething() {
        System.out.println("Father's do something");
        this.doSomething();
    }
    /**
     * 下面演示了重写的用法，来自jdk中的{@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
     */

    public final void acquire(int arg) {
        System.out.println("调用父类默认方法----");
        tryAcquire(arg);
    }

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args){
        Father father = new Son();
//        Father father = new Father();
//        father.doSomething();
        father.acquire(1);
    }
}

class Son extends Father{
    @Override
    public void doSomething() {
        System.out.println("Son's do something");
        super.doSomething();
    }

    @Override
    protected final boolean tryAcquire(int acquires) {
        System.out.println("调用子类实现");
        return true;
    }
}