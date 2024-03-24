package com.test.request

import android.os.Looper

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

    var totalTime = 0L

    fun test(){
        val start  =  System.currentTimeMillis()

        //doSomething

        totalTime += start - totalTime
    }
}