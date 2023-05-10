package com.erix.course.philipp.core_ui.text

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.fonts.interFontFamily

@Composable
fun H3(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
    size: TextUnit = 30.sp,
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontFamily = interFontFamily,
        fontSize = size,
        modifier = modifier,
        textAlign = textAlign,
        color = color,
    )
}

@Composable
@Preview
fun H3Preview() {
    H3(text= "Hello!", color = Color.Yellow)
}



