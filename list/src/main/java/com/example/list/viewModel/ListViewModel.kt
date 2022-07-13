package com.example.list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.common.model.data.TodoData
import com.example.common.model.repository.TodoDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val todoDataRepository: TodoDataRepository
): ViewModel() {

    val todoData: LiveData<List<TodoData>> = todoDataRepository.getAll().asLiveData()

    fun getAllData(): Flow<List<TodoData>> = todoDataRepository.getAll()
}

