package com.finddreams.stockglance

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.lifecycleScope
import com.finddreams.stockglance.data.StockRepository
import com.finddreams.stockglance.glance.MyAppWidget
import com.finddreams.stockglance.ui.theme.StockGlanceTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockGlanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "设置")
                        SettingStockScreen(onClickStock = {
                            StockRepository.setStockInfo(it)
                            updateGlance()
                        }, onClickSkin = {
                            Log.i("MainActivity", "onCreate: 皮肤 $it")
                            StockRepository.setSkinState(it)
                            updateGlance()
                        })

                    }

                }
            }
        }
    }

    private fun updateGlance() {
        lifecycleScope.launch {
            delay(50)
            MyAppWidget().updateAll(this@MainActivity)
        }

        finish()
    }
}
