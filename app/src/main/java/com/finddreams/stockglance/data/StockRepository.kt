package com.finddreams.stockglance.data

import com.finddreams.stockglance.kv.AppKVConfig
import com.finddreams.stockglance.kv.txStock
import com.finddreams.stockglance.model.StockInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object StockRepository {
    private val _stockInfo = MutableStateFlow<StockInfo>(AppKVConfig.saveStockInfo?:txStock)
    val stockInfoState = _stockInfo.asStateFlow()

    fun setStockInfo(stockInfo: StockInfo) {
        _stockInfo.value = stockInfo
        AppKVConfig.saveStockInfo= stockInfo
    }
}