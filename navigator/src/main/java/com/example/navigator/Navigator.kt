package com.example.navigator

import androidx.fragment.app.FragmentActivity
import com.example.common.model.data.TodoData

interface Navigator {
    fun showListFeature()
    fun showEditFeature(activity: FragmentActivity, todoData: TodoData?)
    fun closeEditFeature(activity: FragmentActivity)
}