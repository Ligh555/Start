package com.ligh.viewModul

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModul: ViewModel() {

    val mutableLiveData = MutableLiveData<String>()
    var remainsTime = 2000
    val datas = mutableListOf("ligh","xiaoming")

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
    }

    fun doSomething(){
        datas.forEach{
            println(it)
        }
    }
}