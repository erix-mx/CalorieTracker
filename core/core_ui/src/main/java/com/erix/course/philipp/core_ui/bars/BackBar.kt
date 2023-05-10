package com.erix.course.philipp.core_ui.bars

import androidx.annotation.DrawableRes
import   androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.R

@Composable
fun BackBar(
    modifier: Modifier = Modifier,
    height: Dp = 58.dp,
    tint: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp)
            .height(height),
        contentAlignment = Alignment.CenterStart
    ) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = tint
            )
        }
    }
}

@Composable
@Preview
fun BackBarPreview() {
    BackBar()
}