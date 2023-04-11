package com.ligh.recycleview

import androidx.lifecycle.ViewModel
import java.util.*

class RecycleViewViewModel : ViewModel() {
    private val data = mutableListOf(0)
    init {
        for (i in 1 .. 10){
            data.add(i)
        }
    }

    fun getData() : List<Int>{
        return data
    }

    fun dataSwap(source : Int, target: Int){
        Collections.swap(data,source,target)
    }

    fun dataRemove(position: Int){
        data.remove(position)
    }
}