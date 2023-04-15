package com.ligh.base

import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val fragmentList = listOf(RECYCLE_VIEW, POP_UP, BOTTOM_POPOVER,ANIMATION)

    companion object{
        const val RECYCLE_VIEW = "recycleView"
        const val POP_UP = "popUp"
        const val BOTTOM_POPOVER = "bottom_popover"
        const val ANIMATION = "animation"
    }
}