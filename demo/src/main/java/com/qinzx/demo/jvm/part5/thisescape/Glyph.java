package com.qinzx.demo.jvm.part5.thisescape;

/**
 * 在父类构造函数内部调用具有多态行为的函数将导致无法预测的结果，因为此时子类对象还没初始化。
 * Java中构造函数的调用顺序：
 * （1）在其他任何事物发生之前，将分配给对象的存储空间初始化成二进制0；
 * （2）调用基类构造函数。从根开始递归下去，因为多态性此时调用子类覆盖后的draw()方法（要在调用RoundGlyph构造函数之前调用），
 *      由于步骤1的缘故，我们此时会发现radius的值为0；
 * （3）按声明顺序调用成员的初始化方法；
 * （4）最后调用子类的构造函数。
 *
 * 子类总是会调用父类构造器,之所以需要调用父类的构造方法是因为在父类中，可能存在私有属性需要在其构造方法内初始化；
 * 调用的情况：
 * 1，默认情况，子类总是会调用父类默认无参构造器。
 * 2，在子类构造器中指定需要调用的父类构造器（有/无参都可以），并且必须在子类的构造器中的第一行位置。
 * 3，子类存在多个构造器，如果嵌套使用：（PS：编译期会合并其中的嵌套构造器）
 * 3,1，合并后，默认也是调用父类的无参构造器。
 * 3,2，子类指定父类构造器，这时指定的父类构造器逻辑在子类构造器的首行就好，因为会合并。
 *
 * 构造器的执行并不会创建对象，只有new+构造器的组合语句，才表示创建对象
 * @author qinzx
 * @date 2020/03/27 10:35
 */
public class Glyph {
    void draw() { //没有执行
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Glyph() before draw()");
        draw(); //父类构造器作为子类构造器执行前的默认执行，此时父构造器内执行的方法是子类的重写方法。
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;  //5，初始化变量

    RoundGlyph(int r) {//2，首先调用父类构造器（并且默认是无参构造器）
        radius = r;    //6，赋值执行
        System.out.println("RoundGlyph.RoundGlyph().radius = " + radius);
    }

    @Override
    void draw() {  //4，在父构造器被调用，此时该类（子类）还没被初始化，所以实例变量的值为默认值。
        System.out.println("RoundGlyph.draw().radius = " + radius);
    }
}

class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);//1，首先执行
    }
}