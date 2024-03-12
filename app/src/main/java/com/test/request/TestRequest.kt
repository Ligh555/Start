package com.test.request

import android.os.Looper
import java.util.logging.Handler

class TestRequest {

    val handler = android.os.Handler(Looper.getMainLooper())

    val client = RequestClient {
        handler.post {
            updateUI()
        }
    }

    fun updateUI() {

    }

    fun handleRequest() {
        Request.request(Request.getId(client))
    }
}