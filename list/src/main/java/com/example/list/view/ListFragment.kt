package com.example.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.data.TodoData
import com.example.list.databinding.FragmentTodoListBinding

class ListFragment: Fragment() {

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment().also {

            }
        }
    }

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: TodoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TodoListAdapter {
            println("タップTODO")
        }
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding?.recyclerView?.addItemDecoration( DividerItemDecoration(context, LinearLayoutManager(context).orientation))

        //サンプル
        adapter.submitList(
            listOf(
                TodoData(id=0, title = "ああああ", description = "説明説明説明"),
                TodoData(id=1, title = "いいいい", description = "説明説明説明"),
                TodoData(id=2, title = "うううう", description = "説明説明説明"),
                TodoData(id=3, title = "ええええ", description = "説明説明説明"),
                TodoData(id=4, title = "おおおお", description = "説明説明説明"),
                TodoData(id=5, title = "かかかか", description = "説明説明説明"),
                TodoData(id=6, title = "きききき", description = "説明説明説明"),
                TodoData(id=7, title = "くくくく", description = "説明説明説明"),
                TodoData(id=8, title = "けけけけ", description = "説明説明説明"),
                TodoData(id=8, title = "ここここ", description = "説明説明説明"),
                TodoData(id=5, title = "ささささ", description = "説明説明説明"),
                TodoData(id=6, title = "しししし", description = "説明説明説明"),
                TodoData(id=7, title = "すすすす", description = "説明説明説明"),
                TodoData(id=8, title = "せせせせ", description = "説明説明説明"),
                TodoData(id=8, title = "そそそそ", description = "説明説明説明"),
                TodoData(id=5, title = "たたたた", description = "説明説明説明"),
                TodoData(id=6, title = "ちちちち", description = "説明説明説明"),
                TodoData(id=7, title = "つつつつ", description = "説明説明説明"),
                TodoData(id=8, title = "てててて", description = "説明説明説明"),
                TodoData(id=8, title = "とととと", description = "説明説明説明"),
            )
        )

        binding?.floatingActionButton?.setOnClickListener {
            println("追加")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}