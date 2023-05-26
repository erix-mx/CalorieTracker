package com.erix.course.philipp.tracker_presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.core_ui.theme.ItemFoodBadgeColors
import com.erix.course.philipp.tracker_presentation.R

@Composable
fun ItemFoodBadge(
    modifier: Modifier = Modifier,
    colors: ItemFoodBadgeColors = ItemFoodBadgeColors(),
    text: String = "Vegan",
    @DrawableRes icon: Int = R.drawable.ic_snack,
) {
    Row(
        modifier = modifier
            .padding(end = 6.dp)
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(21.dp)
                .padding(end = 8.dp),
            tint = colors.text
        )
        H3(
            isMaxWidth = false,
            text = text,
            modifier = Modifier
                .padding(end = 8.dp)
                .wrapContentSize(),
            color = colors.text,
            size = 10.sp,
        )
    }
}

@Composable
@Preview
fun ItemFoodBadgePreview() {
    CalorieTrackerTheme {
        ItemFoodBadge()
    }
}