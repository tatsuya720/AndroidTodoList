package com.example.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.*
import com.example.common.model.data.TodoData
import com.example.list.databinding.FragmentTodoListBinding
import com.example.list.viewModel.ListViewModel
import com.example.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: Fragment() {
    @Inject lateinit var navigator: Navigator

    private val viewModel by viewModels<ListViewModel>()
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

        adapter = TodoListAdapter(
            onClick = {
                showEditFeature(requireActivity(), it)
            },
            onCompleteCheckClick = {
                viewModel.setTodoState(it)
            },
            {
                viewModel.deleteTodo(it)
            }
        )

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding?.recyclerView?.addItemDecoration( DividerItemDecoration(context, LinearLayoutManager(context).orientation))

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todoListViewHolder = viewHolder as TodoListAdapter.ViewHolder
                todoListViewHolder.deleteItem()
            }
        })

        itemTouchHelper.attachToRecyclerView(binding?.recyclerView)

        lifecycle.coroutineScope.launchWhenResumed {
            viewModel.list.collect {
                adapter.submitList(it)
            }

        }

        binding?.floatingActionButton?.setOnClickListener {
            showEditFeature(requireActivity(), null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showEditFeature(activity: FragmentActivity, todoData: TodoData?) = navigator.showEditFeature(activity = activity, todoData = todoData)

}