package com.erix.course.philipp.core_ui.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.fonts.interFontFamily

@Composable
fun H1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
    lineHeight: TextUnit = 40.sp,
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontFamily = interFontFamily,
        fontSize = 40.sp,
        modifier = modifier.fillMaxWidth(),
        textAlign = textAlign,
        color = color,
        lineHeight = lineHeight
    )
}

@Composable
fun H1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
    lineHeight: TextUnit = 40.sp,
    rightTag: String = "",
) {
    Text(
        text = buildAnnotatedString {
            append(text)
            withStyle(
                style = SpanStyle().copy(
                    color = color.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 29.sp,
                    fontFamily = interFontFamily,
                )
            ) {
                append(" $rightTag")
            }
        },
        fontWeight = FontWeight.Bold,
        fontFamily = interFontFamily,
        fontSize = 36.sp,
        modifier = modifier.fillMaxWidth(),
        textAlign = textAlign,
        color = color,
        lineHeight = lineHeight
    )
}

@Composable
@Preview
fun H1Preview() {
    H1(text = "Hello!", color = Color.Yellow)
}

@Composable
@Preview
fun H1RightTagPreview() {
    H1(text = "80", color = Color.Yellow, rightTag = "%")
}



