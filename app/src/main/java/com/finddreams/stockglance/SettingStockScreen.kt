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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.ui.unit.sp
import com.finddreams.stockglance.data.StockRepository
import com.finddreams.stockglance.kv.appleStock
import com.finddreams.stockglance.kv.mtStock
import com.finddreams.stockglance.kv.txStock
import com.finddreams.stockglance.model.StockInfo


@Composable
fun SettingStockScreen(
    onClick: (StockInfo) -> Unit
) {
    //3个股票市场的股票 美股
    val stockMarketList = arrayListOf<StockInfo>(
        txStock,
        mtStock,
        appleStock
    )
    LazyColumn() {
        items(stockMarketList) {
            ListItem(
                modifier = Modifier.clickable{
                onClick(it)
                },
                headlineContent = { Text(it.name+"（${it.code}）") },
                trailingContent = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Localized description",
                    )
                },
            )
            HorizontalDivider()
        }
    }
}

@Preview(name = "SettingStockScreen")
@Composable
private fun PreviewSettingStockScreen() {
    SettingStockScreen(){

    }
}