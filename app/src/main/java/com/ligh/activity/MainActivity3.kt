package com.ligh.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.ligh.R
import com.ligh.binding
import com.ligh.databinding.ActivityMain3Binding
import com.ligh.databinding.ActivityMainBinding

class MainActivity3 : BaseActivity() {

    private val bind: ActivityMain3Binding by binding()
    override val viewBinding: ViewBinding
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
}