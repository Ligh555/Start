package com.ligh.activity

import com.ligh.network.Http

class Test {

    companion object{
        fun  test(){
            val listPath = "article/list/0/json"
            Http.doRequest(listPath)
        }
    }

}