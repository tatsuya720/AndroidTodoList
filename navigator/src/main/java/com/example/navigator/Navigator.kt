package com.example.navigator

import com.example.common.model.data.TodoData

interface Navigator {
    fun showListFeature()
    fun showEditFeature(todoData: TodoData)
}