package com.example.edit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.data.TodoData
import com.example.common.model.repository.TodoDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val todoDataRepository: TodoDataRepository
): ViewModel() {

    private val mutCompletedSave = MutableLiveData<Boolean>()
    val completedSave: LiveData<Boolean> = mutCompletedSave

    fun saveTodoData(todoData: TodoData) {
        println("保存開始")
        viewModelScope.launch {
            if(todoData.id == 0L) {
                todoDataRepository.insert(todoData = todoData)
            } else {
                todoDataRepository.update(todoData = todoData)
            }
            mutCompletedSave.value = true
            println("保存終了")
        }
    }

    fun deleteTodoData(todoData: TodoData) {
        viewModelScope.launch {
            todoDataRepository.delete(todoData = todoData)
        }
    }
}