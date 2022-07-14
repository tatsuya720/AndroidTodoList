package com.example.list.view

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.data.TodoData
import com.example.common.model.data.TodoState
import com.example.list.databinding.TodoListItemBinding

class TodoListAdapter(
    private val onClick: (TodoData) -> Unit,
    private val onCompleteCheckClick:(TodoData) -> Unit,
    private val onDelete:(TodoData) -> Unit
): ListAdapter<TodoData, TodoListAdapter.ViewHolder>(TodoDiffCallback) {

    class ViewHolder(
        private val binding: TodoListItemBinding,
        val onClick: (TodoData) -> Unit,
        val onCompleteCheckClick:(TodoData) -> Unit,
        val onDelete: (TodoData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentData: TodoData? = null

        init {
            binding.root.setOnClickListener {
                currentData?.let {
                    onClick(it)
                }
            }

            binding.completeCheck.setOnClickListener {
                println("checkbox click")

              setTextViewStrikethrough(binding.textView, binding.completeCheck.isChecked)

                val todoState = if(binding.completeCheck.isChecked) {
                    TodoState.Complete
                } else {
                    TodoState.NotComplete
                }
                currentData = currentData?.copy(todoState = todoState)

                currentData?.let {
                    onCompleteCheckClick(it)
                }
            }
        }

        fun bind(todoData: TodoData) {
            currentData = todoData
            binding.textView.text = todoData.title
            binding.completeCheck.isChecked = todoData.todoState == TodoState.Complete
            setTextViewStrikethrough(binding.textView, binding.completeCheck.isChecked)
        }

        private fun setTextViewStrikethrough(textView: TextView, isChecked: Boolean) {
            textView.apply {
                paintFlags = if(isChecked) {
                    this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    Paint.ANTI_ALIAS_FLAG
                }
            }
        }

        fun deleteItem() {
            currentData?.let {
                onDelete(it)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, onClick, onCompleteCheckClick, onDelete)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object TodoDiffCallback : DiffUtil.ItemCallback<TodoData>() {
    override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem.title == newItem.title && oldItem.limitDate == newItem.limitDate
    }
}