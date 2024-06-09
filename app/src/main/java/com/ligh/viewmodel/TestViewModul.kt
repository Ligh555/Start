package com.ligh.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TestViewModul: ViewModel() {

    val mutableLiveData = MutableLiveData<String>()
    var remainsTime = 2000
    val datas = mutableListOf("ligh","xiaoming")

    val list = listOf<String>()

    val mutablelist = mutableListOf<String>()

    init {
        val countDount = object : CountDownTimer(2000*1000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainsTime --
                mutableLiveData.postValue(remainsTime.toString())
            }

            override fun onFinish() {
                mutableLiveData.postValue("计时结束")
            }

        }
        countDount.start()

        mutablelist.add("")
    }

    fun doSomething(){
        datas.forEach{
            println(it)
        }
    }

//    fun okhttpGet(){
//        val okHttpClient = OkHttpClient()
//        val request = Request.Builder().url("asdfasd").build()
//        okHttpClient.newCall(request).enqueue(object : Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
}