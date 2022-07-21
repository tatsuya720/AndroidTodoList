package com.example.common.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.formattyyyMMdd(): String {
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
    return sdf.format(this)
}