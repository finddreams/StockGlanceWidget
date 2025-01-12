package com.finddreams.stockglance.data

import com.finddreams.stockglance.kv.AppKVConfig
import com.finddreams.stockglance.kv.txStock
import com.finddreams.stockglance.model.StockInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 *管理stateFlow
 *@Author: finddreams
 *Copyright (c) finddreams https://github.com/finddreams
 */
object StockRepository {
    private val _stockInfo = MutableStateFlow<StockInfo>(AppKVConfig.saveStockInfo?:txStock)
    val stockInfoState = _stockInfo.asStateFlow()

    private val _skinState = MutableStateFlow<Boolean>(AppKVConfig.isDarkSkin)
    val skinState = _skinState.asStateFlow()

    fun setStockInfo(stockInfo: StockInfo) {
        _stockInfo.value = stockInfo
        AppKVConfig.saveStockInfo= stockInfo
    }
    fun setSkinState(isNightSkin: Boolean) {
        _skinState.value = isNightSkin
        AppKVConfig.isDarkSkin = isNightSkin
    }
}