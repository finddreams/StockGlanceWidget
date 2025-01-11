package com.finddreams.stockglance.kv

import com.drake.serialize.serialize.annotation.SerializeConfig
import com.drake.serialize.serialize.serial
import com.finddreams.stockglance.R
import com.finddreams.stockglance.model.StockInfo

@SerializeConfig(mmapID = "app_config") // 指定mmapID可以避免字段名重复情况下导致的错误
object AppKVConfig {
    var saveStockInfo: StockInfo? by serial(txStock)
    var isNightSkin: Boolean by serial(true)
}

val txStock = StockInfo("腾讯控股", "00700", "373.400", "+4.200", "+1.14%", "香港",R.drawable.hk)
val mtStock = StockInfo("贵州茅台", "600519", "1436.00", "-8.00", "-0.55%", "A股",R.drawable.zh)
val appleStock = StockInfo("苹果", "APPL", "234.365", "-8.335", "-3.43%", "美股",R.drawable.us)