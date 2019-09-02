package com.qinzx.demo.kotlin

class FirstDemo(val name: String) {
    fun helloWorld() {
        println("hello, $name")
    }

}

/**
 * 可变长参数，用 vararg 关键字进行标识
 */
fun vars(vararg v:Int){
    for (vt in v) {
        println(vt)
    }
}

fun printSum(a: Int, b: Int): Unit {
    println(a + b)
}
//返回类型自动推断
fun sum(a: Int, b: Int) = a + b
/**
 * public方法必须明确写出返回类型，无返回值类型
 * (Unit类似java中的void)除外
 */
public fun sumOut(a: Int, b: Int):Int = a + b




fun main(args: Array<String>) {
    FirstDemo("tom").helloWorld()
    vars(1,2,4,5)
    printSum(1, 1)
    //lambda(匿名函数)
    var sumLambda: (Int,Int)->Int={x, y->x+y}
    println(sumLambda(1,2))
    /**
     * 定义常量与变量
     * var 可变变量
     * val 不可变变量，类似java中final
     */
    val a: Int = 1;
    val b = 1
    val c: Int

    /*块注释嵌套
    /*sfsdkfldsf
    sdflkjsklf*/
    块注释嵌套
     */
    c = 1
    var x = 5
    x += 1

    //类型后面加?表示可为空
    var age: String? = "23"
//    var ageNull: String = null
    var ageNull: String? = null
    //抛出空指针异常
    val ages = age!!.toInt()
    //不做处理返回null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1

}
