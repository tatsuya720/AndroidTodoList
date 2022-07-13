package com.example.edit.view

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.common.model.data.TodoData
import com.example.edit.R
import com.example.edit.databinding.FragmentTodoEditBinding
import com.example.edit.viewModel.EditViewModel
import com.example.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EditFragment : Fragment(), DatePickerDialog.OnDateSetListener {


    @Inject lateinit var navigator: Navigator

    private val viewModel by viewModels<EditViewModel>()
    private var _binding: FragmentTodoEditBinding? = null
    private val binding get() = _binding

    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoEditBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.titleBar?.setNavigationOnClickListener {
            closeEditFeature()
        }

        //表示さえせるデータが渡されている場合
        args.editTodoData?.let { todoData ->
            binding?.editTitle?.setText(todoData.title)
            binding?.editDescription?.setText(todoData.description)

            val sdf = SimpleDateFormat("yyyy/MM/dd")
            val limitDateStr = sdf.format(todoData.limitDate)
            binding?.editLimitDate?.setText(limitDateStr)
        }

        //期限タップイベント
        binding?.editLimitDate?.setOnClickListener {
            setOnClickLimitDate(args.editTodoData?.limitDate)
        }

        binding?.titleBar?.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_save -> {
                    println("保存ボタンクリック")
                    saveTodoData(createAddTodoData())
                }
                R.id.action_delete -> {
                    //データがある場合のみ処理
                    args.editTodoData?.let { todoData ->
                        println("削除ボタンクリック")
                        deleteTodoData(todoData)
                        closeEditFeature()
                    }
                }
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val selectDate = Calendar.getInstance().apply {
            set(year, month, day)
        }.time

        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val selectDateStr = sdf.format(selectDate)
        binding?.editLimitDate?.setText(selectDateStr)
    }

    private fun closeEditFeature() = navigator.closeEditFeature(requireActivity())

    private fun saveTodoData(todoData: TodoData) = viewModel.saveTodoData(todoData = todoData)

    private fun deleteTodoData(todoData: TodoData) = viewModel.deleteTodoData(todoData = todoData)

    private fun createAddTodoData(): TodoData {
        val title = binding?.editTitle?.text.toString()
        val description = binding?.editDescription?.text.toString()

        val limitDate = binding?.editLimitDate?.let {
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            sdf.parse(it.text.toString())
        } ?: Date()

        //既存のものの書き換えならidをつかう
        //新規なら0
        val id = args.editTodoData?.let {
            it.id
        } ?: 0

        return TodoData(id, title, description, limitDate = limitDate)
    }

    private fun setOnClickLimitDate(limitDate: Date?) {
        val calendar = Calendar.getInstance()
        calendar.time = limitDate ?: Date()
        val datePickerDialog = DatePickerDialog(requireContext(),
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
}