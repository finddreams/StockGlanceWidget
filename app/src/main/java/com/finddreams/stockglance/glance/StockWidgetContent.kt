package com.finddreams.stockglance.glance

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.finddreams.stockglance.MainActivity
import com.finddreams.stockglance.R
import com.finddreams.stockglance.model.StockInfo

@Composable
fun StockWidgetContent(stock: StockInfo, isNight: Boolean) {
    Log.i("StockWidgetContent", "StockWidgetContent: stock ${stock.name} isNight $isNight")
    Box(
        modifier = GlanceModifier.fillMaxSize()
            .cornerRadius(12.dp),
    ) {
        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(getBgColor(isNight))
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
        ) {
            // 顶部栏
            Row(
                horizontalAlignment = Alignment.Start,
                modifier = GlanceModifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        provider = ImageProvider(stock.icon),
                        contentDescription = "market",
                        modifier = GlanceModifier.size(24.dp)
                    )
                    Spacer(GlanceModifier.width(8.dp))
                    Text(
                        stock.market, style = TextStyle(
                            color = getBgColor(!isNight),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(GlanceModifier.width(48.dp))
                    Image(
                        provider = ImageProvider(R.drawable.setting),
                        contentDescription = "Settings",
                        modifier = GlanceModifier.size(20.dp)
                            .clickable(actionStartActivity<MainActivity>())
                    )
                }
            }
            Spacer(GlanceModifier.height(12.dp))
            // 股票信息
            Column {
                Text(
                    stock.name,
                    style = TextStyle(
                        color = getStockNameColor(isNight),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(GlanceModifier.height(3.dp))
                Text(
                    stock.code,
                    style = TextStyle(color = ColorProvider(Color.Gray), fontSize = 14.sp)
                )

                Spacer(GlanceModifier.height(10.dp))
                Row {
                    Text(
                        stock.price,
                        style = TextStyle(
                            color = getColorByChange(stock.change),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                // 涨跌信息
                Row {
                    Text(
                        stock.change,
                        style = TextStyle(
                            color = getColorByChange(stock.change),
                            fontSize = 14.sp
                        )
                    )
                    Spacer(GlanceModifier.width(4.dp))
                    Text(
                        stock.changePercent,
                        style = TextStyle(
                            color = getColorByChange(stock.change),
                            fontSize = 14.sp
                        )
                    )
                }

            }
        }
    }
}

@Composable
private fun getColorByChange(change: String): ColorProvider {
    val zdf = change.toFloatOrNull()
    return when {
        zdf != null && zdf > 0 -> ColorProvider(R.color.widget_text_color_red)
        zdf != null && zdf < 0 -> ColorProvider(R.color.widget_text_color_green)
        else -> ColorProvider(R.color.widget_text_color)
    }
}

private fun getStockNameColor(isNight: Boolean): ColorProvider {
    return if (isNight) {
        ColorProvider(R.color.widget_text_color)
    } else {
        ColorProvider(R.color.black)
    }
}

private fun getBgColor(isNight: Boolean): ColorProvider {
    return ColorProvider(
        if (isNight) Color(0xFF000000) // 使用硬编码颜色值
        else Color(0xFFFFFFFF)
    )
}