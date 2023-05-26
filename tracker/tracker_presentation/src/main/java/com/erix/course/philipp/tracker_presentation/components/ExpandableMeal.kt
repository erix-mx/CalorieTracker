package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.form.ItemBackground
import com.erix.course.philipp.core_ui.items.ItemInfoGrams
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.core_ui.theme.ItemBackgroundColors
import com.erix.course.philipp.core_ui.theme.ItemInfoGramsColors

@Composable
fun ExpandableMeal(
    modifier: Modifier = Modifier,
    tagColor: Color = Color.White,
    infoGramsColors: ItemInfoGramsColors = ItemInfoGramsColors(),
    itemBackgroundColors: ItemBackgroundColors = ItemBackgroundColors(),
    meal: Meal = Meal(),
    onToggleClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(36.dp))
            .background(itemBackgroundColors.colorBackground)
    ){
        ItemBackground(
            click = onToggleClick,
            icon = meal.drawableRes,
            colorBackground = itemBackgroundColors.colorBackground,
            colorIconBackground = itemBackgroundColors.colorIconBackground,
            tintIconColor = itemBackgroundColors.tintIconColor,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier
                        .weight(1f)
                        .padding(end = 8.dp, start = 12.dp),
                ) {
                    H3(
                        text = meal.name.asString(context),
                        color = tagColor,
                        textAlign = TextAlign.Start,
                        size = 19.sp,
                    )
                    Normal(
                        text = "${meal.calories} Kcal",
                        color = tagColor,
                        textAlign = TextAlign.Start,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .wrapContentSize(),
                ) {
                    ItemInfoGrams(
                        tag = "Proteins",
                        count = meal.protein.toString(),
                        unit = "g",
                        colors = infoGramsColors
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ItemInfoGrams(
                        tag = "Carbs",
                        count = meal.carbs.toString(),
                        unit = "g",
                        colors = infoGramsColors
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ItemInfoGrams(
                        tag = "Fats",
                        count = meal.fat.toString(),
                        unit = "g",
                        colors = infoGramsColors
                    )
                }

            }
        }
        AnimatedVisibility(visible = meal.isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 16.dp),
            ) {
                content()
            }
        }

        ToggleMealButton(
            iconColor = tagColor,
            isExpanded = meal.isExpanded,
            onToggle = onToggleClick
        )

    }
}

@Composable
@Preview
fun ItemFoodPreview() {
    ExpandableMeal(
        tagColor = Color.White,
        infoGramsColors = ItemInfoGramsColors(
            background = Color.Red,
            text = Color.White
        )
    )
}