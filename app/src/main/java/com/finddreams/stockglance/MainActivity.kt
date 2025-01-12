package com.finddreams.stockglance

import android.app.ComponentCaller
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.finddreams.stockglance.data.StockRepository
import com.finddreams.stockglance.glance.MyAppWidget
import com.finddreams.stockglance.glance.MyAppWidgetReceiver
import com.finddreams.stockglance.kv.AppKVConfig
import com.finddreams.stockglance.kv.txStock
import com.finddreams.stockglance.ui.theme.StockGlanceTheme
import com.finddreams.stockglance.utils.requestAddWidget

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val selectCode = AppKVConfig.saveStockInfo?.code?: txStock.code
            StockGlanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "设置")

                        AppWidgetSettingScreen(selectCode,AppKVConfig.isDarkSkin,onClickStock = {
                            StockRepository.setStockInfo(it)
                            Toast.makeText(this@MainActivity, "切换股票:${it.name}", Toast.LENGTH_SHORT).show()
                            MyAppWidget().forceUpdate(this@MainActivity)
                            refreshWidget(this@MainActivity)
                            finish()
                        }, onClickSkin = {
                            Log.i("MainActivity", "onCreate: 皮肤 $it")
                            Toast.makeText(this@MainActivity, "切换皮肤:${if (it) "黑色" else "白色"}", Toast.LENGTH_SHORT).show()
                            StockRepository.setSkinState(it)
                            MyAppWidget().forceUpdate(this@MainActivity)
                            refreshWidget(this@MainActivity)
                            finish()
                        }, onClickSetWidget = {
                            requestAddWidget(this@MainActivity, 100)
                            finish()
                        })

                    }

                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        if (requestCode == 100) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshWidget(context: Context) {
        val intent = Intent(context, MyAppWidgetReceiver::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        val appWidgetIds = AppWidgetManager.getInstance(context)
            .getAppWidgetIds(ComponentName(context, MyAppWidgetReceiver::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        context.sendBroadcast(intent)
    }

}

