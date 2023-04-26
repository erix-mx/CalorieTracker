package com.erix.course.philipp.core_presentation.screen_style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_presentation.canvas.Circle


@Composable
fun AppBackground(
    modifier: Modifier = Modifier,
    circleColor: Color = Color.Black,
    content: @Composable () -> Unit,
) {
    val screenWidthPx = with(LocalConfiguration.current) { screenWidthDp.toFloat() }
    val screenHeightDp = with(LocalConfiguration.current) { screenHeightDp }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = modifier
                .fillMaxSize()
                .offset(y = screenHeightDp.dp / 1.6f)
        ) {
            Circle(
                radius = screenWidthPx * 3f,
                color = circleColor
            )
        }

        content()
    }
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    device = Devices.PIXEL_4)
fun AppBackgroundPreview() {
    AppBackground {}
}
