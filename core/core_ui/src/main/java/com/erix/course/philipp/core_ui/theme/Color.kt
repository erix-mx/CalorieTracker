package com.erix.course.philipp.core_ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

val avocadoBlue = Color(0xFF4B8AE9)
val avocadoGreen = Color(0xFF55E57D)
val avocadoGreenLight = Color(0xFFBFF6CF)
val avocadoDark = Color(0xFF1E211E)
val avocadoGray = Color(0x33E3FBE9)
val avocadoLight = Color(0xFFD9F4E1)
val avocadoLighter = Color(0xFFF3FFF7)

data class ItemInfoGramsColors(
    val background: Color = Color.Black,
    val text: Color = Color.White,
)

data class ItemBackgroundColors(
    val colorBackground: Color = Color.Black,
    val colorIconBackground: Color = Color.Yellow,
    val tintIconColor: Color = Color.Black,
    val borderColor: Color = Color.White,
)
