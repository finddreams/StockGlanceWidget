package com.finddreams.stockglance.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.glance.material3.ColorProviders


var LightColors: ColorScheme= lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onBackground= LightColor
)
var DarkColors: ColorScheme= darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onBackground= DarkColor
)

object MyAppWidgetGlanceColorScheme {

    val colors = ColorProviders(
        light = LightColors,
        dark = DarkColors
    )
    val colorSkinLight=ColorProviders(LightColors)
    val colorSkinDark=ColorProviders(DarkColors)
}