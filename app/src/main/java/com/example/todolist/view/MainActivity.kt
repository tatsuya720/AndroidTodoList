package com.example.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.list.view.ListFragment
import com.example.navigator.Navigator
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
//        val fragment = ListFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.container, fragment)
//        transaction.commit()
    }
}