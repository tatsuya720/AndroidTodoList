package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.domain.repository.TodoDataRepository
import com.example.domain.repository.TodoDataRepositoryImpl
import com.example.database.dao.TodoDao
import com.example.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesTodoDao(
        @ApplicationContext context: Context
    ): TodoDao {
        val database = Room.databaseBuilder(context, AppDatabase::class.java, "todo-list-database").build()

        return database.todoDao()
    }

    @Singleton
    @Provides
    fun providesTodoDataRepository(
        todoDao: TodoDao
    ): TodoDataRepository {
        return TodoDataRepositoryImpl(todoDao = todoDao)
    }
}