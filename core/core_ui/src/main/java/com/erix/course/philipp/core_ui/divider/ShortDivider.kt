package com.erix.course.philipp.core_ui.divider

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.LocalSpacing

@Composable
fun ShortDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    val dimensions = LocalSpacing.current
    Divider(
        color = color.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = dimensions.horizontalGlobalPadding)
    )
}