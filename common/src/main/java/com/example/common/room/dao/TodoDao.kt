package com.example.common.room.dao

import androidx.room.*
import com.example.common.model.data.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todoData: TodoData)

    @Update
    suspend fun update(todoData: TodoData)

    @Delete
    suspend fun delete(todoData: TodoData)

//    @Query("select * from todo_list_table")
//    suspend fun getAll(): Flow<List<TodoData>>
//
//    @Query("select * from todo_list_table where id = :id")
//    suspend fun getTodo(id: Long): Flow<TodoData>
}