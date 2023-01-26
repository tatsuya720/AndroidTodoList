package com.example.domain.repository

import com.example.data.TodoData
import com.example.database.dao.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoDataRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoDataRepository {

    override suspend fun insert(todoData: TodoData):Long {
        return todoDao.insert(todoData)
    }

    override suspend fun update(todoData: TodoData) {
        todoDao.update(todoData)
    }

    override suspend fun delete(todoData: TodoData) {
        todoDao.delete(todoData)
    }

    override fun getAll(): Flow<List<TodoData>> = todoDao.getAll()

}