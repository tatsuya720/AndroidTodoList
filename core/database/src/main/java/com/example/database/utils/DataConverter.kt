package com.example.utils.database

import androidx.room.TypeConverter
import com.example.data.TodoState
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

    @TypeConverter
    fun fromTodoStateInt(value: Int): TodoState {
        return TodoState.find(value) ?: TodoState.NotComplete
    }

    @TypeConverter
    fun TodoStateToInt(todoState: TodoState): Int {
        return todoState.value
    }
}