package com.qinzx.demo.kotlin.entity

import java.math.BigDecimal
import java.util.*

/**
 *
 * @author qinzx
 * @date 2019/09/24 20:38
 */
data class Book(
        val id: Long? = null,
        val create: Date? = null,
        val price: BigDecimal = BigDecimal.ZERO,
        val valid: Boolean? = null
)