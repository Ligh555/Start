package com.test.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class TestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.i("touch", "testview dispatchTouchEvent: ${event.action}")
        return super.onTouchEvent(event)
    }

    init {
        setOnClickListener {
            Log.i("touch", "testview onclick")
        }
    }
}