package com.example.common.model.repository

import com.example.common.model.data.TodoData
import com.example.common.room.dao.TodoDao
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class TodoDataRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoDataRepository {

    override suspend fun insert(todoData: TodoData) {
        todoDao.insert(todoData)
    }

    override suspend fun update(todoData: TodoData) {
        todoDao.update(todoData)
    }

    override suspend fun delete(todoData: TodoData) {
        todoDao.delete(todoData)
    }

    override fun getAll(): Flow<List<TodoData>> = todoDao.getAll()

}