package com.example.list.viewModel

import kotlinx.coroutines.flow.collect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.data.TodoData
import com.example.common.model.repository.TodoDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val todoDataRepository: TodoDataRepository
) : ViewModel() {

    private val _list = MutableStateFlow<List<TodoData>>(emptyList())
    val list: StateFlow<List<TodoData>> = _list

    init {
        viewModelScope.launch {
            todoDataRepository.getAll().collect {
                _list.value = it
            }


        }

    }

    fun setTodoState(todoData: TodoData) {
        viewModelScope.launch {
            todoDataRepository.update(todoData)
        }
    }

    fun deleteTodo(todoData: TodoData) {
        viewModelScope.launch {
            todoDataRepository.delete(todoData)
        }
    }
}

