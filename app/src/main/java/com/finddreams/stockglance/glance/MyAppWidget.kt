package com.finddreams.stockglance.glance

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
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
import com.finddreams.stockglance.data.StockRepository
import com.finddreams.stockglance.kv.AppKVConfig
import com.finddreams.stockglance.kv.txStock
import com.finddreams.stockglance.model.StockInfo
import com.finddreams.stockglance.ui.theme.DarkColors
import com.finddreams.stockglance.ui.theme.LightColors
import com.finddreams.stockglance.ui.theme.MyAppWidgetGlanceColorScheme
import com.finddreams.stockglance.ui.theme.MyAppWidgetGlanceColorScheme.colorSkinDark
import com.finddreams.stockglance.ui.theme.MyAppWidgetGlanceColorScheme.colorSkinLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/**
 * GlanceWidget 股票小部件
 *@Author: finddreams
 *Copyright (c) finddreams https://github.com/finddreams
 */
class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.
        provideContent {
            // create your AppWidget here
            val repository = remember {
                StockRepository
            }
//            // Retrieve the cache data everytime the content is refreshed
           val stockState=repository.stockInfoState.collectAsState()
           val skinState=repository.skinState.collectAsState()
            val skinValue =skinState.value
            val stockValue = stockState.value
            Log.i("MyAppWidget", "provideGlance: skinValue:$skinValue")
            GlanceTheme(
                colors = if (skinValue) {
                    colorSkinDark
                } else {
                    colorSkinLight
                }
            ) {
                StockWidgetContent(stockValue, skinValue)
            }
        }
    }

    fun forceUpdate(context: Context) {
        CoroutineScope(Dispatchers.Default).launch {
            GlanceAppWidgetManager(context).getGlanceIds(MyAppWidget::class.java).forEach { glanceId ->
                update(context, glanceId)
            }
        }
    }
}

