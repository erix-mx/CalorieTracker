package com.erix.course.philipp.core_ui.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.fonts.interFontFamily

@Composable
fun Normal(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontFamily = interFontFamily,
        fontSize = 18.sp,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = color,
    )
}

@Composable
@Preview
fun NormalPreview() {
    Normal(text= "Hello!", color = Color.Yellow)
}
