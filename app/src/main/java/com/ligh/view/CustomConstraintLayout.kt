package com.ligh.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout

class CustomConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val fillPaint: Paint = Paint()
    private var fillTranslationX: Float = 0f

    init {
        fillPaint.color = 0xFFFF0000.toInt() // 设置填充颜色为红色
    }

    fun animateFillTranslationX(targetX: Float,view : View) {
        val animator = ObjectAnimator.ofFloat(view, "fillTranslationX", fillTranslationX, targetX)
        animator.duration = 500 // 持续时间为500毫秒，你可以根据需要调整
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制填充颜色作为背景
        canvas.drawRect(
            0f,
            height.toFloat() - 8,  // 离底部8个像素的位置
            width.toFloat(),
            height.toFloat(),
            fillPaint
        )
    }
    // 重写此方法以处理View的背景绘制
    override fun dispatchDraw(canvas: Canvas) {
        // 调用父类的dispatchDraw方法，保证子View的绘制不受影响
        super.dispatchDraw(canvas)
    }
}
