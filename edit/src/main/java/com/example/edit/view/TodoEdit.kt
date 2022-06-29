package com.example.edit.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.edit.R
import com.example.edit.databinding.FragmentTodoEditBinding
import com.example.edit.viewModel.TodoEditViewModel
import javax.inject.Inject

class TodoEdit : Fragment() {

    companion object {
        fun newInstance() = TodoEdit()
    }

    val viewModel by viewModels<TodoEditViewModel>()
    private var _binding: FragmentTodoEditBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoEditBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}