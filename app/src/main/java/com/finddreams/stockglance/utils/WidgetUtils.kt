package com.finddreams.stockglance.utils

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.widget.Toast
import com.finddreams.stockglance.MainActivity
import com.finddreams.stockglance.glance.MyAppWidgetReceiver


 fun requestAddWidget(activity: Activity, requestCode: Int) {
    val appWidgetManager = AppWidgetManager.getInstance(activity)

    // 检查是否支持固定小组件功能
    if (appWidgetManager.isRequestPinAppWidgetSupported) {
        // 获取小组件提供者的 ComponentName
        val myWidgetProvider = ComponentName(activity, MyAppWidgetReceiver::class.java)

        // 创建 PinAppWidgetRequest
        val pinnedWidgetCallback = PendingIntent.getActivity(
            activity,
            requestCode,
            Intent(activity, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        appWidgetManager.requestPinAppWidget(myWidgetProvider, null, pinnedWidgetCallback)
    } else {
        Toast.makeText(activity, "设备不支持动态添加小组件", Toast.LENGTH_SHORT).show()
    }
}
