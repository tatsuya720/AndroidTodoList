package com.example.common.ext

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(): Date? {
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
    return sdf.parse(this)
}