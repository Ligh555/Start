package com.test.recycleview

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.math.log

class TestFlow {

    companion object {
        const val TAG = "ligh TestFlow"
    }


    suspend fun test() {


        flow {
            repeat(3) {
                emit(it + 1)
            }
        }
        flowOf(1, 2, 3).buffer().collect {
            delay(1000)
            Log.i(TAG, "buffer: $it")
        }

        flowOf(1,2,3).onEach {
           // it = it+1 // 编译器异常

            Log.i(TAG, "onEach: it")
        }

        flowOf(4, 5, 6).buffer(1, BufferOverflow.DROP_OLDEST).collect {
            delay(1000)
            Log.i(TAG, "buffer DROP_OLDEST: $it")
        }
    }


}