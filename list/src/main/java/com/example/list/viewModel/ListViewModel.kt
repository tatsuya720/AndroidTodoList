package com.example.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.data.TodoData
import com.example.common.model.repository.TodoDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val todoDataRepository: TodoDataRepository
) : ViewModel() {

    enum class SortType {
        LimitDateAsc,
        LimitDateDesc,
    }

    private var _sortType: SortType = SortType.LimitDateAsc

    private val _list = MutableStateFlow<List<TodoData>>(emptyList())
    val list: StateFlow<List<TodoData>> = _list

    init {
        getList()
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

    fun getList(sortType: SortType? = null) {

        //ソートの指定があったら書き換え
        sortType?.let {
            _sortType = it
        }

        viewModelScope.launch {
            todoDataRepository.getAll()
                .catch {  }
                .collect { list ->

                    val sortedList = if(_sortType == SortType.LimitDateDesc) {
                        list.sortedByDescending {
                            it.limitDate
                        }
                    } else {
                        list.sortedBy {
                            it.limitDate
                        }
                    }
                    _list.value = sortedList
                }


        }
    }
}

