package com.example.database.dao

import androidx.room.*
import com.example.data.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todoData: TodoData): Long

    @Update
    suspend fun update(todoData: TodoData)

    @Delete
    suspend fun delete(todoData: TodoData)

    @Query("select * from todo_list_table")
    fun getAll(): Flow<List<TodoData>>
}