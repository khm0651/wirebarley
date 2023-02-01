package com.example.wirebarley.core.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toLocalDateTime(): String{
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return sdf.format(this)
}