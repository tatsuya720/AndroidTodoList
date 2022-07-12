package com.example.common.room.database

import androidx.room.TypeConverter
import java.util.*

class DataConverter {
    @TypeConverter
    fun fromTimeStamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}