package com.ligh.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity

class BottomDialog(context: Context) : Dialog(context) {
    init {
        window?.attributes?.gravity = Gravity.BOTTOM
    }
}