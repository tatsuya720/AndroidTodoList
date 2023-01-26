package com.example.list.view

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.*
import com.example.data.TodoData
import com.example.list.R
import com.example.list.databinding.FragmentTodoListBinding
import com.example.list.viewModel.ListViewModel
import com.example.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ListFragment : Fragment() {
    @Inject
    lateinit var navigator: Navigator

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

        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
        )

        settingItemTouchHelper()

        lifecycle.coroutineScope.launchWhenResumed {
            viewModel.list.collect {
                binding?.taskNothingMessage?.isVisible= it.isEmpty()
                adapter.submitList(it)
            }
        }

        binding?.floatingActionButton?.setOnClickListener {
            showEditFeature(requireActivity(), null)
        }

        binding?.titleBar?.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.date_asc -> {
                    viewModel.getList(ListViewModel.SortType.LimitDateAsc)
                }
                R.id.date_desc -> {
                    viewModel.getList(ListViewModel.SortType.LimitDateDesc)
                }
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showEditFeature(activity: FragmentActivity, todoData: TodoData?) =
        navigator.showEditFeature(activity = activity, todoData = todoData)

    private fun settingItemTouchHelper() {
        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
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

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )

                    val itemView = viewHolder.itemView

                    if (dX == 0f && !isCurrentlyActive) {
                        clearCanvas(
                            c,
                            itemView.right + dX.toInt(),
                            itemView.top,
                            itemView.right,
                            itemView.bottom
                        )
                        super.onChildDraw(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            false
                        )
                        return
                    }

                    context?.let {
                        //スワイプ分の領域を赤で塗りつぶす
                        val background = ColorDrawable()
                        background.color = ContextCompat.getColor(it, com.example.common.R.color.red_500)
                        background.setBounds(
                            itemView.right + dX.toInt(),
                            itemView.top,
                            itemView.right,
                            itemView.bottom
                        )
                        background.draw(c)

                        val icon = ContextCompat.getDrawable(it, com.example.common.R.drawable.ic_baseline_delete_forever_24)
                        icon?.let { drawable ->
                            val iconMargin = (itemView.height - drawable.intrinsicHeight) / 2
                            val iconTop = itemView.top + iconMargin
                            val iconLeft = itemView.right - iconMargin - drawable.intrinsicHeight
                            val iconRight = itemView.right - iconMargin
                            val iconBottom = iconTop + drawable.intrinsicHeight

                            drawable.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                            drawable.draw(c)
                        }
                    }


                }

                private fun clearCanvas(c: Canvas, left: Int, top: Int, right: Int, bottom: Int) {
                    val paint = Paint()
                    paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
                    c.drawRect(
                        left.toFloat(),
                        top.toFloat(),
                        right.toFloat(),
                        bottom.toFloat(),
                        paint
                    )
                }
            })

        itemTouchHelper.attachToRecyclerView(binding?.recyclerView)
    }
}