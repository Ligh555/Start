package com.test.coroutine

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ligh.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.yield
import kotlin.math.log

class TestViewModel : BaseViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i(TAG, coroutineContext.toString() + throwable.toString())
    }

    fun test() {
        viewModelScope.launch(exceptionHandler) {
            launch() {
                throw IllegalAccessError("1")
            }
        }
        viewModelScope.launch() {
           runCatching { throw IllegalAccessError("2") }
        }

        viewModelScope.launch(exceptionHandler) {
            Log.i(TAG, "test: 1")
            val job = launch {

               while (true){
                   Log.i(TAG, "test: 1")
               }

            }
            Log.i(TAG, "test: 4")
            delay(100)
            job.cancel()
            Log.i(TAG, "test: 5")
        }

        val mutex = Mutex()

        viewModelScope.launch {
            mutex.withLock {
                //doSomething
            }
        }
    }
}