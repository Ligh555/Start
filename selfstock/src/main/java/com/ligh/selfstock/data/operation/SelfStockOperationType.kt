package com.ligh.selfstock.data.operation

import androidx.annotation.IntDef


@IntDef(SelfStockOperationType.addSelfStock, SelfStockOperationType.removeSelfStock)
@Retention(AnnotationRetention.SOURCE)
annotation class SelfStockOperationType {
    companion object {
        //加自选
        const val addSelfStock = 0
        //减自选
        const val removeSelfStock = 1
    }
}