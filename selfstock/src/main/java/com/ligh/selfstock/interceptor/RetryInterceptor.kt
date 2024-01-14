package com.ligh.selfstock.interceptor

import com.ligh.selfstock.data.Response

class RetryInterceptor(private val maxRetry: Int = 3) : IInterceptor {


    private var count = 0
    override fun interceptor(chain: AbsChain): Response {
        var response = chain.proceed()
        while (response.isRetry() && count < maxRetry) {
            count++
            response = chain.proceed()
        }
        return response
    }
}