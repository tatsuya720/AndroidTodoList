package com.example.todolist.common

import android.content.Context
import com.example.common.model.data.TodoData
import com.example.navigator.Navigator
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
): Navigator {

    override fun showListFeature() {
        println("showListFeature")
    }

    override fun showEditFeature(todoData: TodoData) {
        println("showEditFeature")
    }
}