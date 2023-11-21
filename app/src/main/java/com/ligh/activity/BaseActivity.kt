package com.ligh.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity : AppCompatActivity() {
    abstract val viewBinding: ViewBinding
}