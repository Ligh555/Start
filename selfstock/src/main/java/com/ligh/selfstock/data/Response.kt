package com.ligh.selfstock.data

class Response(val code: Int = ERROR) {
    fun isSuccess() = code != ERROR

    fun isRetry()  = code == RETRY
}

const val ERROR = -1
const val SUCCESS = 0
const val RETRY = 1
