package com.ligh.activity

import android.content.Context
import android.util.Log
import com.ligh.download.TestDownLoad
import com.ligh.network.Http
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object Test {

    fun test(context :Context) {
        okhttp()

        TestDownLoad.startDownLoad(context)
    }

    private fun okhttp() {
        val listPath = "article/list/0/json"
        Http.doRequest(listPath)

        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("https://www.wanandroid.com/$listPath")
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("OkHttp", "Call Failed:" + e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(
                    "OkHttp",
                    "Call succeeded:" + response.message + response.code + response.body?.byteString()
                )
            }
        })
    }
}
