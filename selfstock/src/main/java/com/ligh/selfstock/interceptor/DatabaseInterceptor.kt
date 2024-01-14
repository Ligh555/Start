package com.ligh.selfstock.interceptor

import com.ligh.selfstock.data.Response

class DatabaseInterceptor:IInterceptor {
    override fun interceptor(chain: AbsChain): Response {
        return Response()
    }
}