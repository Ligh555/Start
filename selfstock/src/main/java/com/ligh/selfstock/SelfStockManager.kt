package com.ligh.selfstock

import com.ligh.selfstock.data.Response
import com.ligh.selfstock.data.StockGroup
import com.ligh.selfstock.data.operation.Operation
import com.ligh.selfstock.interceptor.AbsChain
import com.ligh.selfstock.interceptor.Chain
import com.ligh.selfstock.interceptor.DatabaseInterceptor
import com.ligh.selfstock.interceptor.LocalInterceptor
import com.ligh.selfstock.interceptor.RemoteInterceptor

object SelfStockManager {


    var isLogin = false

    private var allStockGroup: List<StockGroup> = emptyList()


    fun handleOperation(operation: Operation): Response {
        val response = createInterceptor(operation).proceed()
        allStockGroup = AbsChain.allStockGroup
        return response
    }

    private fun createInterceptor(operation: Operation) = Chain(0, operation).apply {
        with(interceptors) {
            if (isLogin) {
                add(RemoteInterceptor())
                add(LocalInterceptor())
                add(DatabaseInterceptor())
            } else {
                add(LocalInterceptor())
                add(DatabaseInterceptor())
            }
        }
    }
}