package com.ligh.selfstock.interceptor

import com.ligh.selfstock.data.Response
import com.ligh.selfstock.data.StockGroup
import com.ligh.selfstock.data.operation.OperationAdd
import com.ligh.selfstock.data.operation.OperationRemove

class RemoteInterceptor : IInterceptor {

    companion object {
        val allStockGroup: MutableList<StockGroup> = mutableListOf()
    }


    override fun interceptor(chain: AbsChain): Response {
        when (chain.operation) {
            is OperationAdd -> {

            }

            is OperationRemove -> {

            }
        }
        AbsChain.allStockGroup = allStockGroup
        return chain.proceed()
    }
}