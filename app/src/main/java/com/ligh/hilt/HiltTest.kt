package com.ligh.hilt

import android.util.Log
import javax.inject.Inject

class HiltTest @Inject constructor() {

    private val TAG: String = "HiltTest"

    fun test() {
        Log.i(TAG, "test: ++")
    }
}