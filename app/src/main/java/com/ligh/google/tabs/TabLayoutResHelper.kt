package com.ligh.google.tabs

import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.R
import com.google.android.material.internal.ThemeEnforcement


class TabLayoutResHelper(private val mView: TabLayout, private val defaultRes: Int) {

    private var mTextColorResId: Int = 0
    private var mSelectTextColorResId: Int = 0
    private var mTabIndicatorId: Int = 0
    private var mTabIndicatorColorId: Int = 0

    // TODO:  1:this 2: 替换Hxui
    private var mTabBackgroundResId: Int = 0


    var isChange = false

    fun collectSkinRes(attrs: AttributeSet, defStyleAttr: Int) {
        ThemeEnforcement.obtainStyledAttributes(
            mView.context,
            attrs,
            R.styleable.TabLayout,
            defStyleAttr,
            defaultRes,
            R.styleable.TabLayout_tabTextAppearance
        ).apply {
            kotlin.runCatching {
                mTextColorResId = getResourceId(R.styleable.TabLayout_tabTextColor, mTextColorResId)
                mSelectTextColorResId =
                    getResourceId(R.styleable.TabLayout_tabSelectedTextColor, mSelectTextColorResId)
                mTabIndicatorId =
                    getResourceId(R.styleable.TabLayout_tabIndicator, mTabIndicatorId)
                mTabIndicatorColorId =
                    getResourceId(R.styleable.TabLayout_tabIndicatorColor, mTabIndicatorColorId)
                mTabBackgroundResId =
                    getResourceId(R.styleable.TabLayout_tabBackground, mTabBackgroundResId)

            }.onFailure {
                it.printStackTrace()
            }.also {
                recycle()
            }

        }
        applySkin()
    }

    fun applySkin() {
        var colorStateList: ColorStateList? = null
        if (isChange) {
            mTextColorResId = com.ligh.R.color.teal_200
            mSelectTextColorResId = com.ligh.R.color.black
            mTabIndicatorColorId = com.ligh.R.color.white
        } else {
            mTextColorResId = com.ligh.R.color.purple_200
            mSelectTextColorResId = com.ligh.R.color.purple_700
            mTabIndicatorColorId = com.ligh.R.color.black
        }
        isChange = !isChange
        mTextColorResId.takeIf { it != 0 }?.let { id ->
            colorStateList = AppCompatResources.getColorStateList(mView.context, mTextColorResId)
        }
        mSelectTextColorResId.takeIf { it != 0 }?.let { id ->
            colorStateList?.let { color ->
                colorStateList = TabLayout.createColorStateList(
                    color.defaultColor,
                    ContextCompat.getColor(mView.context, id)
                )
            }
        }
        colorStateList?.let {
            mView.setTabTextColors(colorStateList)
        }

        mTabIndicatorId.takeIf { it != 0 }?.let {
            mView.setSelectedTabIndicator(
                AppCompatResources.getDrawable(
                    mView.context,
                    it
                )
            )
        }
        mTabIndicatorColorId.takeIf { it != 0 }?.let {
            mView.setSelectedTabIndicatorColor(
                ContextCompat.getColor(
                    mView.context,
                    it
                )
            )
        }
    }
}