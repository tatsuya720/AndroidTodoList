package com.example.common.model.repository

import com.example.common.model.data.TodoData
import kotlinx.coroutines.flow.Flow

interface TodoDataRepository {
    suspend fun insert(todoData: TodoData)

    suspend fun update(todoData: TodoData)

    suspend fun delete(todoData: TodoData)

    fun getAll(): Flow<List<TodoData>>
}