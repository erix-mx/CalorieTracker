package com.erix.course.philipp.core_ui.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.fonts.interFontFamily



@Composable
fun H2(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontFamily = interFontFamily,
        fontSize = 23.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        textAlign = textAlign,
        color = color,
    )
}

@Composable
fun H2(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontFamily = interFontFamily,
        fontSize = 23.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        textAlign = textAlign,
        color = color,
    )
}

@Composable
@Preview
fun H2Preview() {
    H2(
        text = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur",
        color = Color.Yellow
    )
}
