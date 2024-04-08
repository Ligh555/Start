package com.test.coordinatorLayout

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.ligh.R

class DependentView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mStartX = 0
    private var mStartY = 0
    init {
        isClickable = true
        setBackgroundColor(resources.getColor(R.color.teal_200))
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartX = event.rawX.toInt()
                mStartY = event.rawY.toInt()
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = event.rawX - mStartX
                val dy = event.rawY - mStartY
                offsetLeftAndRight(dx.toInt())
                offsetTopAndBottom(dy.toInt())

                mStartX = event.rawX.toInt()
                mStartY = event.rawY.toInt()
            }
        }
        return super.onTouchEvent(event)
    }

}