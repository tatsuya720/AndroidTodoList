package com.example.domain.repository

import com.example.data.TodoData
import kotlinx.coroutines.flow.Flow

interface TodoDataRepository {
    suspend fun insert(todoData: TodoData):Long

    suspend fun update(todoData: TodoData)

    suspend fun delete(todoData: TodoData)

    fun getAll(): Flow<List<TodoData>>
}