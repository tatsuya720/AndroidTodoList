package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.TodoData
import com.example.database.dao.TodoDao
import com.example.utils.database.DataConverter

@Database(entities = [TodoData::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}