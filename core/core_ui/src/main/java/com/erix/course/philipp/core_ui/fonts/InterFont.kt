package com.erix.course.philipp.core_ui.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.erix.course.philipp.core_ui.R

val interFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_medium, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.inter_extra_bold, FontWeight.ExtraBold),
    Font(R.font.inter_extra_light, FontWeight.ExtraLight),
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_black, FontWeight.Black),
    Font(R.font.inter_thin, FontWeight.Thin)
)