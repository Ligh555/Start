package com.ligh.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ligh.R
import com.ligh.databinding.ActivityMainBinding
import com.ligh.viewModul.TestViewModul
import java.util.*

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<TestViewModul>()

    lateinit var bind : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        viewModel.mutableLiveData.observe(this
        ) { t -> t?.let {
            bind.tvRemaim.setText(it)
        } }
    }
}