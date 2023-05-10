package com.erix.course.philipp.core_ui.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.fonts.interFontFamily
import com.erix.course.philipp.core_ui.text.H1

@Composable
fun ValueIndicator(
    color: Color = Color.Black,
    value: String,
    unit: String,
    textAlign: TextAlign = TextAlign.Center,
    rightTag: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        H1(
            modifier = Modifier.weight(1f).padding(horizontal = 21.dp),
            text = value,
            color = color,
            textAlign = textAlign,
            rightTag = rightTag
        )
        Text(
            fontSize = 21.sp,
            fontFamily = interFontFamily,
            text = unit,
            color = color.copy(alpha = 0.6f),
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun ValueIndicatorPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ){  ValueIndicator(
        value = "1.5",
        unit = "kg"
    ) }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun ValueIndicatorStartPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ){  ValueIndicator(
        value = "1.5",
        unit = "kg",
        textAlign = TextAlign.Start
    ) }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun ValueIndicatorRightTagPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ){  ValueIndicator(
        value = "20",
        unit = "Carbs",
        textAlign = TextAlign.Start,
        rightTag = "%"
    ) }
}