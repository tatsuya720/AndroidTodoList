package com.example.edit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.edit.databinding.FragmentTodoEditBinding
import com.example.edit.viewModel.TodoEditViewModel
import com.example.navigator.Navigator
import javax.inject.Inject

class EditFragment : Fragment() {

    val viewModel by viewModels<TodoEditViewModel>()

    @Inject lateinit var navigator: Navigator

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

        _binding?.testText?.text = args.editTodoData.title
//        _binding?.toolBar?.setNavigationOnClickListener {
//            println("navigationOnClickListener")
//            navigator.closeEditFeature()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}