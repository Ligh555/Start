package com.ligh.base

import androidx.lifecycle.ViewModel

abstract  class BaseViewModel : ViewModel() {

    val TAG  = this::class.simpleName
}