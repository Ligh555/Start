package com.ligh.selfstock.interceptor

import com.ligh.selfstock.data.StockGroup
import com.ligh.selfstock.data.Response
import com.ligh.selfstock.data.operation.Operation

interface IInterceptor {
    fun interceptor(chain: AbsChain): Response
}
