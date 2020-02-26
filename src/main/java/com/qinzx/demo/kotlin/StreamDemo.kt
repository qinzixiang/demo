package com.qinzx.demo.kotlin

import com.qinzx.demo.kotlin.entity.Book
import java.text.SimpleDateFormat

/**
 * kotlin 使用stream 倒序排序
 * @ClassName: StreamDemo
 * @Author qinzx
 * @Date 2019/09/24 20:37
 * @Copyright (C) 杭州同基汽车科技有限公司
 */

fun main(args: Array<String>) {
    val list = ArrayList<Book>()
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val parse1 = sdf.parse("2019-09-02 08:25:26")
    list.add(Book(id = 1, create = parse1))
    val parse2 = sdf.parse("2019-08-02 12:20:26")
    list.add(Book(id = 2, create = parse2))
    val parse3 = sdf.parse("2019-09-09 19:25:26")
    list.add(Book(id = 3, create = parse3))
    println(list)
    val sortedByDescending = list.sortedByDescending { it.create }
    println(sortedByDescending)
    //List<Object>转Map<Field, Object>
    val objList = listOf(User("1", 22), User("2", 18))
    val ageMap = objList.associateBy({ it.age }, { it })
}

data class User(var name: String? = null, var age: Int)