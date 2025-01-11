package com.finddreams.stockglance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.finddreams.stockglance.kv.AppKVConfig
import com.finddreams.stockglance.kv.appleStock
import com.finddreams.stockglance.kv.mtStock
import com.finddreams.stockglance.kv.txStock
import com.finddreams.stockglance.model.StockInfo

/**
 * 股票小部件点击打开的 设置页面
 *@Author: finddreams
 *Copyright (c) finddreams https://github.com/finddreams
 */
@Composable
fun SettingStockScreen(
    onClickStock: (StockInfo) -> Unit,
    onClickSkin: (Boolean) -> Unit
) {
    //3个股票市场的股票 美股
    val stockMarketList = arrayListOf<StockInfo>(
        txStock,
        mtStock,
        appleStock
    )
    val skinList = arrayListOf<Boolean>(
        false,
        true,
    )
    LazyColumn() {
        item {
            ListItem(
                headlineContent = { Text("股票", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            )
            HorizontalDivider()
        }

        items(stockMarketList) {
            ListItem(
                modifier = Modifier.clickable {
                    onClickStock(it)
                },
                headlineContent = { Text(it.name + "（${it.code}）") },
                trailingContent = {
                    if (AppKVConfig.saveStockInfo?.code==it.code){
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "",
                            tint = Color.Blue
                        )
                    }
                },
            )
            HorizontalDivider()
        }
        item {
            ListItem(
                headlineContent = { Text("皮肤", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            )
            HorizontalDivider()
        }
        items(skinList) {
            ListItem(
                modifier = Modifier.clickable {
                    onClickSkin(it)
                },
                headlineContent = { Text(if (it) "深色" else "浅色") },
                trailingContent = {
                    if (AppKVConfig.isNightSkin==it){
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "",
                            tint = Color.Blue
                        )
                    }
                },
            )
            HorizontalDivider()
        }
    }
}

@Preview(name = "SettingStockScreen")
@Composable
private fun PreviewSettingStockScreen() {
    SettingStockScreen(onClickStock = {}, onClickSkin = {})
}