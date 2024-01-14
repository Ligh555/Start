package com.ligh.selfstock.data.operation

import com.ligh.selfstock.data.StockGroup
import com.ligh.selfstock.data.StockInfo

abstract class Operation(@SelfStockOperationType open val type: Int) {
    var currGroups: List<StockGroup> = emptyList()
}

data class OperationAdd constructor(
    @SelfStockOperationType
    override val type: Int,
    val groupId: String,
    val stockInfo: StockInfo
) : Operation(type)

data class OperationRemove(@SelfStockOperationType override val type: Int) : Operation(type)