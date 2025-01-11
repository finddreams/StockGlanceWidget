package com.finddreams.stockglance.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.glance.material3.ColorProviders
import androidx.glance.unit.ColorProvider
import com.finddreams.stockglance.ui.theme.DarkColor


var LightColors: ColorScheme= lightColorScheme(
    secondary = LightColor,
    tertiary = LightColor,
    background= LightColor,
    onBackground= LightColor,
    surface= LightColor,
    onSurface= LightColor
)
var DarkColors: ColorScheme= darkColorScheme(
    primary = DarkColor,
    secondary = DarkColor,
    tertiary = DarkColor,
    background= DarkColor,
    onBackground= DarkColor,
    surface= DarkColor,
    onSurface= DarkColor
)

object MyAppWidgetGlanceColorScheme {

    val colors = ColorProviders(
        light = LightColors,
        dark = DarkColors
    )
    val colorSkinLight=ColorProviders(LightColors)
    val colorSkinDark=ColorProviders(DarkColors)
}