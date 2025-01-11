package com.finddreams.stockglance.model

import java.io.Serializable
/**
 * 股票信息model
 *@Author: finddreams
 *Copyright (c) finddreams https://github.com/finddreams
 */
class StockInfo(
    val name: String,
    val code: String,
    val price: String,
    val change: String,
    val changePercent: String,
    val market: String,
    val icon: Int
): Serializable {
}