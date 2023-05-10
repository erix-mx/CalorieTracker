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
import com.erix.course.philipp.core_ui.text.H3

@Composable
fun ValueSimpleIndicator(
    color: Color = Color.Black,
    value: String,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        H3(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            color = color,
            textAlign = TextAlign.Start
        )
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun ValueSimpleIndicatorPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ){  ValueSimpleIndicator(
        value = "Male",
    ) }
}