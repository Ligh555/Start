package com.ligh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BarLineChartView extends View {
    private static final int NUM_BARS = 5;
    private static final int NUM_DATA_POINTS = 60;

    private float[] barData = new float[NUM_DATA_POINTS];
    private float[] lineData = new float[NUM_DATA_POINTS];

    private Paint barPaint;
    private Paint linePaint;
    private Paint textPaint;
    private Paint horizontalLinePaint;

    public BarLineChartView(Context context) {
        super(context);
        init();
    }

    public BarLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 初始化柱状图画笔
        barPaint = new Paint();
        barPaint.setColor(Color.BLUE);
        barPaint.setStyle(Paint.Style.FILL);

        // 初始化折线图画笔
        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);

        // 初始化文本画笔
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(24);

        // 初始化水平线画笔
        horizontalLinePaint = new Paint();
        horizontalLinePaint.setColor(Color.GRAY);
        horizontalLinePaint.setStyle(Paint.Style.STROKE);
        horizontalLinePaint.setStrokeWidth(2);

        // 填充示例数据，你应该替换这里的数据为你自己的数据
        for (int i = 0; i < NUM_DATA_POINTS; i++) {
            barData[i] = (float) (Math.random() * 50); // 示例柱状图数据
            lineData[i] = (float) (Math.random() * 50); // 示例折线图数据
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / (2 * NUM_DATA_POINTS);
        int horizontalLineGap = height / 6;

        // 绘制水平线
        for (int i = 1; i <= 5; i++) {
            float y = i * horizontalLineGap;
            canvas.drawLine(0, y, width, y, horizontalLinePaint);

            // 绘制纵坐标标签
            String yLabel = String.valueOf(i * 10.0f);
            float textWidth = textPaint.measureText(yLabel);
            canvas.drawText(yLabel, -textWidth - 10, y + textPaint.getTextSize() / 2, textPaint);
        }

        // 绘制柱状图和折线图
        for (int i = 0; i < NUM_DATA_POINTS; i++) {
            float barHeight = (barData[i] / 50) * height;
            float x = i * barWidth * 2 + barWidth;

            // 绘制柱状图
            canvas.drawRect(x - barWidth / 2, height - barHeight, x + barWidth / 2, height, barPaint);

            // 绘制折线图
            float y = (1 - (lineData[i] / 50)) * height;
            if (i > 0) {
                float prevX = (i - 1) * barWidth * 2 + barWidth;
                float prevY = (1 - (lineData[i - 1] / 50)) * height;
                canvas.drawLine(prevX, prevY, x, y, linePaint);
            }

            // 绘制折线图上的点
            canvas.drawPoint(x, y, linePaint);

            // 绘制横坐标标签
            String xLabel = String.valueOf(i);
            float textWidth = textPaint.measureText(xLabel);
            canvas.drawText(xLabel, x - textWidth / 2, height + 40, textPaint);
        }
    }
}



