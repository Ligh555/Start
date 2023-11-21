package com.ligh.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class RoundedRectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        color = resources.getColor(android.R.color.holo_orange_light)
        style = Paint.Style.FILL
    }

    private val path = Path()
    private val rectF = RectF()

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = resources.getColor(android.R.color.white)
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }


    init {
        setLayerType(LAYER_TYPE_HARDWARE, null) // 启用硬件加速
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.translate(20f,0f)

        val rectWidth = width.toFloat() / 6
        val rectHeight = height.toFloat() / 3

        val cornerRadius = rectHeight / 2 // 圆角半径

        // 计算矩形的位置
        val width = width.toFloat()
        val height = height.toFloat()
        rectF.set(width - rectWidth, 0f, width + 10, rectHeight)

        // 使用Path绘制带圆角的矩形
        path.reset()
        path.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)

        // 在Canvas上绘制Path
        canvas.drawPath(path, paint)

        // 计算文字位置使其居中在矩形中
        val x = rectF.centerX()
        val y = rectF.centerY() - (textPaint.ascent() + textPaint.descent()) / 2

        // 在Canvas上绘制文字
        textPaint.color = resources.getColor(android.R.color.holo_orange_light)
        canvas.drawText("测试1", x, y, textPaint)
    }
}
