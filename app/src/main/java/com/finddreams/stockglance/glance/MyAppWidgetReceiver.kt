package com.finddreams.stockglance.glance

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
/**
 *@Author: finddreams
 *Copyright (c) finddreams https://github.com/finddreams
 */
class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()}