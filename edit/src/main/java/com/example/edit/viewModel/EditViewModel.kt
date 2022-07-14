package com.example.edit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.data.TodoData
import com.example.common.model.data.TodoState
import com.example.common.model.repository.TodoDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val todoDataRepository: TodoDataRepository
): ViewModel() {

    private val mutCompletedSaveId = MutableLiveData<Long>()
    val completedSaveId: LiveData<Long> = mutCompletedSaveId

    var todoData:TodoData? = null
        private set

    fun saveTodoData(title: String, description: String, limitDate: Date = Date()) {
        println("保存開始")
        todoData = TodoData(todoData?.id ?: 0, title, description, limitDate, todoData?.todoState ?: TodoState.NotComplete)
        todoData?.let {
            viewModelScope.launch {
                var id = it.id
                if(id == 0L) {
                    id = todoDataRepository.insert(todoData = it)
                    //idをつかって、TodoDataを新しくする
                    setTodoData(TodoData(id, title, description, limitDate, it.todoState))
                } else {
                    todoDataRepository.update(todoData = it)
                }
                mutCompletedSaveId.value = id
                println("保存終了")
            }
        }
    }

    fun deleteTodoData() {
        todoData?.let {
            viewModelScope.launch {
                todoDataRepository.delete(todoData = it)
            }
        }
    }

    fun setTodoData(todoData:TodoData) {
        this.todoData = todoData
    }
}