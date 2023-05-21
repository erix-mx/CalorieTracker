package com.erix.course.philipp.core_ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.theme.ItemInfoGramsColors

@Composable
fun ItemInfoGrams(
    modifier: Modifier = Modifier,
    tag: String = "",
    count: String = "",
    unit: String = "",
    colors: ItemInfoGramsColors = ItemInfoGramsColors()
) {

    Box(
        modifier = modifier
            .width(42.dp)
            .clip(CircleShape)
            .background(colors.background)
            .padding(horizontal = 4.dp)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            H3(
                text = "${count}${unit}",
                size = 11.sp,
                color = colors.text
            )
            H3(
                text = tag,
                size = 8.sp,
                color = colors.text
            )
        }
    }
}

@Composable
@Preview(
    heightDp = 100,
)
fun ItemInfoGramsPreview() {
    ItemInfoGrams(
        tag = "Proteins",
        count = "10",
        unit = "g",
        colors = ItemInfoGramsColors()
    )
}

@Composable
@Preview(
    heightDp = 100,
)
fun ItemInfoGramsRowPreview() {
    Row(
        modifier = Modifier.fillMaxSize(),
    ) {
        ItemInfoGrams(
            tag = "Proteins",
            count = "134",
            unit = "g",
            colors = ItemInfoGramsColors()
        )
        Spacer(modifier = Modifier.size(8.dp))
        ItemInfoGrams(
            tag = "Carbs",
            count = "198",
            unit = "g",
            colors = ItemInfoGramsColors()
        )
        Spacer(modifier = Modifier.size(8.dp))
        ItemInfoGrams(
            tag = "Fats",
            count = "45",
            unit = "g",
            colors = ItemInfoGramsColors()
        )
    }
}

