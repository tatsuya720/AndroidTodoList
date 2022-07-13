package com.example.todolist.common

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.common.model.data.TodoData
import com.example.list.view.ListFragmentDirections
import com.example.navigator.Navigator
import com.example.todolist.R
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
): Navigator {

    override fun showListFeature() {
        println("showListFeature")
    }

    override fun showEditFeature(activity: FragmentActivity, todoData: TodoData?) {
        println("showEditFeature")
        val action = ListFragmentDirections.actionShowEdit(editTodoData = todoData)
        activity.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun closeEditFeature(activity: FragmentActivity) {
        println("closeEditFeature")
        activity.findNavController(R.id.nav_host_fragment).popBackStack()
    }
}