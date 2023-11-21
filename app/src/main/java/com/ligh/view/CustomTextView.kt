package com.ligh.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ligh.R

@SuppressLint("UseCompatLoadingForDrawables")
class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    init {
        val drawable = context.getDrawable(R.mipmap.ic_launcher)?.apply {
            setBounds(0,0,context.resources.getDimensionPixelSize(R.dimen.dp14),context.resources.getDimensionPixelSize(R.dimen.dp14))
        }
        setCompoundDrawables(null,null,drawable,null)
    }
}
