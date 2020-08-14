package com.qinzx.demo.kotlin

import com.qinzx.demo.kotlin.entity.Book
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import javax.validation.constraints.Min
import kotlin.collections.ArrayList

/**
 * kotlin 使用stream 倒序排序
 * @author qinzx
 * @date 2019/09/24 20:37
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
//    val objList = listOf(User("1", 22), User("2", 18))
//    val ageMap = objList.associateBy({ it.age }, { it })
    //列表累计函数
    val garageData = ArrayList<Book>()
    garageData.add(Book(1, Date(), BigDecimal("12.32"), false))
    garageData.add(Book(2, Date(), BigDecimal("13.00"), true))
    garageData.add(Book(3, Date(), BigDecimal("15"), false))
    val fold = garageData.fold(BigDecimal.ZERO) { acc: BigDecimal, repairDataStatisticsDO: Book ->
        acc.add(repairDataStatisticsDO.price)
    }
    println("累计价格：$fold")
    //根据布尔型字段排序
    println(garageData)
    garageData.sortByDescending { it.valid }
    println(garageData)
}

data class User(@Length(max = 5, min = 2) var name: String? = null, @Min(5) var age: Int)