package com.ligh

import com.ligh.network.Http
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val listPath = "article/list/0/json"
       Http.doRequest(listPath)
    }

}