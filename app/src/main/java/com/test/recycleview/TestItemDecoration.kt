package com.test.recycleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * 实现itemview 吸顶效果
 *
 * 原理是recycleview 在绘制时，调用顺序如下
 *  1.ItemDecoration.onDraw() ，绘制画布最下层
 *  2.recycleview.itemview.ondraw()
 *  3.ItemDecoration.onDrawOver() 绘制在画布最上层
 *
 *  todo 逻辑有问题，暂未完善
 *
 *  需要注意问题
 *  1、 存在recycleview 存在padding下
 *  2.、 吸顶时怎么计算高度
 *  3. 文字居中怎么计算
 *
 *  回答
 *  2. 计算出顶部view底部 和 吸顶栏底部 的最小值为底部基准， 减去 吸顶栏高度 加上 文字高度 /2
 *  3 .文字绘制时以baseline 为基准 ，上面时ascent ，下面是dsceent ，高度为ascent + descent， 由于坐标系以baseline为基准
 *  ascent 为 负值，所以高度为 descent - ascent，
 *  同时绘制时以baseline 为y轴坐标，所以y为  容器高度 /2  + (descent - ascent) / 2  -   descent
 *
 */

class TestItemDecoration : RecyclerView.ItemDecoration() {

    companion object{
        const val text = "测试"
    }

    private val mPaint = Paint()

    private val mTextPaint = Paint().apply {
        color = Color.WHITE
        textSize = 50f
    }

    private val textRect = Rect()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position == 0 || position == 5) {
            outRect.set(0, dp2px(view.context, 100f), 0, 0)
        } else {
            outRect.set(0, 0, 0, 0)
        }

    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        parent.children.forEachIndexed { index, view ->
//            if (index == 0 || index == 5) { //为什么不使用这个呢，原因是想要绘制的itemview 在移动过程中是变化的
//
//            }
            c.drawRect(
                Rect(
                    view.paddingLeft,
                    view.top - dp2px(parent.context, 100f),
                    view.right - view.paddingRight,
                    view.top
                ), mPaint
            )
            mTextPaint.getTextBounds(text, 0, text.length, textRect)
            c.drawText(
                text,
                view.left + parent.paddingLeft + 50.toFloat(),
                view.top  - dp2px(
                    parent.context,
                    100f
                ).toFloat() / 2 + textRect.height() / 2,
                mTextPaint
            )
        }
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val linearLayoutManager = parent.layoutManager as? LinearLayoutManager
        linearLayoutManager?.let {
            val firstPosition = it.findFirstVisibleItemPosition()
            parent.findViewHolderForLayoutPosition(firstPosition)?.itemView?.let { itemView ->
               if (firstPosition  + 1 == 5){
                   c.drawRect(
                       itemView.paddingLeft.toFloat(),
                       itemView.paddingTop.toFloat(),
                       parent.width - parent.paddingRight.toFloat(),
                       Math.min(dp2px(parent.context, 100f).toFloat(),itemView.width.toFloat()),
                       mPaint
                   )

                   // 计算出底部位置，然后往上推
                   val bottom = Math.min(itemView.paddingTop.toFloat() + dp2px(parent.context, 100f).toFloat(),itemView.bottom.toFloat())
                   c.drawText(
                       text,
                       parent.left + parent.paddingLeft + 50.toFloat(),
                       bottom -  dp2px(parent.context, 100f) / 2  + textRect.height() / 2,
                       mTextPaint
                   )
               }else{
                   c.drawRect(
                       parent.paddingLeft.toFloat(),
                       parent.paddingTop.toFloat(),
                       parent.width - parent.paddingRight.toFloat(),
                       dp2px(parent.context, 100f).toFloat(),
                       mPaint
                   )
                   mTextPaint.getTextBounds(text, 0, text.length, textRect)
                   c.drawText(
                       text,
                       parent.left + parent.paddingLeft + 50.toFloat(),
                       parent.top + parent.paddingTop.toFloat() + dp2px(
                           parent.context,
                           100f
                       ).toFloat() / 2 + textRect.height() / 2,
                       mTextPaint
                   )
               }
            }
        }
    }



    private fun dp2px(context: Context, dpValve: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dpValve * density * 0.5f).toInt()
    }
}