package com.example.edit.view

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.common.ext.parseDate
import com.example.common.ext.formattyyyMMdd
import com.example.common.model.data.TodoData
import com.example.common.model.data.TodoState
import com.example.edit.R
import com.example.edit.databinding.FragmentTodoEditBinding
import com.example.edit.viewModel.EditViewModel
import com.example.navigator.Navigator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
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
            viewModel.setTodoData(todoData)
            binding?.editTitle?.setText(todoData.title)
            binding?.editDescription?.setText(todoData.description)

            val limitDateStr = todoData.limitDate.formattyyyMMdd()
            binding?.editLimitDate?.setText(limitDateStr)

            binding?.titleBar?.title = resources.getText(R.string.edit)
        } ?: run {
            viewModel.setTodoData(TodoData(0, "", "", Date(), TodoState.NotComplete))
            binding?.titleBar?.menu?.findItem(R.id.action_delete)?.isVisible = false

            val limitDateStr = Date().formattyyyMMdd()
            binding?.editLimitDate?.setText(limitDateStr)

            binding?.titleBar?.title = resources.getText(R.string.add)
        }

        //期限タップイベント
        binding?.editLimitDate?.setOnClickListener {
            setOnClickLimitDate(viewModel.todoData?.limitDate)
        }

        binding?.titleBar?.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_save -> {
                    val title = binding?.editTitle?.text.toString()
                    val description = binding?.editDescription?.text.toString()
                    val limitDate = binding?.editLimitDate?.text?.toString()?.parseDate() ?: Date()

                    saveTodoData(title = title, description = description, limitDate = limitDate)
                }
                R.id.action_delete -> {
                    //データがある場合のみ処理
                    deleteTodoData()
                    closeEditFeature()
                }
            }
            false
        }

        //保存完了スナックバー表示
        viewModel.completedSaveId.observe(viewLifecycleOwner) {
            //ちゃんと登録されていれば削除ボタンを表示
            if(it != 0L ) {
                binding?.titleBar?.menu?.findItem(R.id.action_delete)?.isVisible = true
            }
            Snackbar.make(requireView(), resources.getText(R.string.edit_saved), Snackbar.LENGTH_SHORT).show()
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

        val selectDateStr = selectDate.formattyyyMMdd()
        binding?.editLimitDate?.setText(selectDateStr)
    }

    private fun closeEditFeature()
        = navigator.closeEditFeature(requireActivity())

    private fun saveTodoData(title:String, description:String, limitDate: Date = Date())
        = viewModel.saveTodoData(title, description, limitDate)

    private fun deleteTodoData()
        = viewModel.deleteTodoData()

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