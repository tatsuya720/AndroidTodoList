package com.example.common.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.common.model.data.TodoData
import com.example.common.room.dao.TodoDao

@Database(entities = [TodoData::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}