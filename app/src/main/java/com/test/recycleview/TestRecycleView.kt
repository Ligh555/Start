package com.test.recycleview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TestRecycleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    init {
        adapter = TestAdapter(context)
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(TestItemDecoration())
    }
}