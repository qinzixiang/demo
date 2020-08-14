package com.qinzx.demo.kotlin

import java.lang.Integer.parseInt

fun main(args: Array<String>) {
//    if (args.size < 2) {
//        print("Two integers expected")
//        return
//    }
    val x = null ?:1
//    val x = parseInt(args[0])
    val y = 2
    // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
    if (x != null && y != null) {
        // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
        print(x * y)
    }
}