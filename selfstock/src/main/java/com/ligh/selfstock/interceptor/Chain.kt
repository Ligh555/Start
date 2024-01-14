package com.ligh.selfstock.interceptor

import com.ligh.selfstock.data.Response
import com.ligh.selfstock.data.StockGroup
import com.ligh.selfstock.data.operation.Operation

class Chain(private val index: Int, override val operation: Operation) : AbsChain() {

    override val interceptors = mutableListOf<IInterceptor>()


    override fun proceed(): Response {
        if (index < interceptors.size) {
            return interceptors[index].interceptor(copy(index + 1, operation, interceptors))
        }

        return Response()
    }


    private fun copy(index: Int, operation: Operation, interceptors: List<IInterceptor>) =
        Chain(index, operation).apply {
            this.interceptors.addAll(interceptors)
        }

}

abstract class AbsChain {
    companion object {

        var allStockGroup: List<StockGroup> = emptyList()
    }

    abstract val operation: Operation


    abstract val interceptors: MutableList<IInterceptor>

    abstract fun proceed(): Response


}