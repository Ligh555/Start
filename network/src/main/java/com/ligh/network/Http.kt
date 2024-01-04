package com.ligh.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class Http {

    private val baseUrl = "https://www.wanandroid.com/"
    private val client = OkHttpClient()
    companion object {
        private val http = Http()
        fun doRequest(path :String):String{
          return  http.doRequest(path)
        }

    }

    fun doRequest(path :String) :String{
        val request = Request.Builder().url(baseUrl + path).build()
        thread {
            client.newCall(request).execute().apply {
                Log.i("http", "request $path $code $body")
                println("request $path $code ${body?.byteString()}")
            }
        }
        return  ""
    }


}