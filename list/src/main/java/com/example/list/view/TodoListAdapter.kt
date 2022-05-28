package com.example.list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.data.TodoData
import com.example.list.databinding.TodoListItemBinding

class TodoListAdapter(private val onClick: (TodoData) -> Unit):
    ListAdapter<TodoData, TodoListAdapter.ViewHolder>(TodoDiffCallback) {

    class ViewHolder(
        private val binding: TodoListItemBinding,
        val onClick: (TodoData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentData: TodoData? = null

        init {
            binding.root.setOnClickListener {
                currentData?.let {
                    onClick(it)
                }
            }
        }

        fun bind(todoData: TodoData) {
            currentData = todoData
            binding.textView.text = todoData.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object TodoDiffCallback : DiffUtil.ItemCallback<TodoData>() {
    override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem.id == newItem.id
    }
}