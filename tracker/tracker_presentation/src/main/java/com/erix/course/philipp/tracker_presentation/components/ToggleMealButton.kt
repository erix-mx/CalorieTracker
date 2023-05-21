package com.erix.course.philipp.tracker_presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.tracker_presentation.R

@Composable
fun ToggleMealButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.ic_chevron,
    isExpanded: Boolean = false,
    iconColor: Color = Color.Black,
    onToggle: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onToggle() }
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(21.dp)
                .rotate(if (isExpanded) 90f else -90f),
            painter = painterResource(id = icon),
            contentDescription = "toggle meal button",
            tint = iconColor.copy(alpha = 0.5f)
        )
    }
}

@Composable
@Preview
fun ToggleMealButtonPreview() {
    ToggleMealButton()
}