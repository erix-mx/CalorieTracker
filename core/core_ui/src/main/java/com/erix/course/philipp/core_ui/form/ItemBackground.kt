package com.erix.course.philipp.core_ui.form

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.R

@Composable
fun ItemBackground(
    modifier: Modifier = Modifier,
    colorBackground: Color = Color.Black,
    colorIconBackground: Color = Color.Yellow,
    @DrawableRes icon: Int? = null,
    tintIconColor: Color = Color.Black,
    borderColor: Color = Color.White,
    selected: Boolean = false,
    click: () -> Unit = { },
    content: @Composable () -> Unit
) {
    val borderStroke = if (selected) modifier.border(6.dp, borderColor, RoundedCornerShape(36.dp)) else modifier
    Box(
        modifier = borderStroke
            .clip(RoundedCornerShape(36.dp))
            .clickable { click() }
            .fillMaxWidth()
            .height(100.dp)
            .background(colorBackground)
            .padding(14.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(29.dp))
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(colorIconBackground),
                contentAlignment = Alignment.Center
            ) {
                icon?.let {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        tint = tintIconColor,
                        painter = painterResource(id = it),
                        contentDescription = stringResource(id = R.string.tag_description_big_button)
                    )
                }
            }

            content()
        }
    }
}



@Composable
@Preview(device = Devices.PIXEL_4)
fun ItemBackgroundPreview() {
    ItemBackground(
        icon = R.drawable.ic_arrow_next,
    ) {}
}

