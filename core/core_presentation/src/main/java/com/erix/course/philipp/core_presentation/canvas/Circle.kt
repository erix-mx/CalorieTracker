package com.erix.course.philipp.core_presentation.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Circle(
    modifier: Modifier = Modifier,
    radius: Float = 100f,
    color: Color = Color.Red
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        rotate(degrees = 45f) {
            drawCircle(
                color = color,
                radius = radius,
                center = center
            )
        }
    }
}

@Preview
@Composable
fun CirclePreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Circle(
            radius = 100f,
            color = Color.Red
        )
    }
}