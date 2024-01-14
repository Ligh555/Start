package com.ligh.selfstock.data

data class StockGroup(
    val id: String,
    val name: String,
    val stockList: List<StockInfo>
)