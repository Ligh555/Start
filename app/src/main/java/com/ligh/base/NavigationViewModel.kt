package com.ligh.base

import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val fragmentList = listOf(RECYCLE_VIEW, POP_UP)

    companion object{
        const val RECYCLE_VIEW = "recycleView"
        const val POP_UP = "popUp"
    }
}