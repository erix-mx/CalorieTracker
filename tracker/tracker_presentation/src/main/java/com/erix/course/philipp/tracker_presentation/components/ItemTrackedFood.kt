package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.core_ui.theme.ItemFoodBadgeColors
import com.erix.course.philipp.core_ui.theme.ItemTrackedFoodColors
import com.erix.course.philipp.tracker_presentation.R
import com.erix.models.tracker.MealType
import com.erix.models.tracker.TrackedFood
import java.time.LocalDate

@Composable
fun ItemTrackedFood(
    modifier: Modifier = Modifier,
    trackedFood: TrackedFood,
    colors: ItemTrackedFoodColors = ItemTrackedFoodColors(),
    onDelete: (() -> Unit)? = null,

    ) {

    val nutrientColors = ItemFoodBadgeColors(
        text = colors.text,
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(colors.background)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(29.dp))
                    .height(76.dp)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                if (trackedFood.imageUrl != null) {
                    AsyncImage(
                        modifier = Modifier
                            .background(colors.text.copy(alpha = 0.1f))
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        model = trackedFood.imageUrl,
                        contentDescription = trackedFood.name,
                        placeholder = painterResource(id = R.drawable.ic_food_placeholder),
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .background(colors.text.copy(alpha = 0.1f))
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_food_placeholder),
                        contentDescription = null
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
            ) {

                Row(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    H3(
                        modifier = Modifier.weight(1f),
                        text = trackedFood.name,
                        color = colors.text,
                        textAlign = TextAlign.Start,
                        size = 16.sp,
                    )
                    onDelete?.let {
                        IconButton(onClick = it) {
                            Icon(
                                modifier = Modifier.size(19.dp),
                                tint = colors.text,
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = null
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(4.dp))
                Normal(
                    text = stringResource(
                        id = R.string.nutrient_info,
                        trackedFood.amount,
                        trackedFood.calories
                    ),
                    color = colors.text,
                    textAlign = TextAlign.Start,
                    size = 11.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    ItemFoodBadge(
                        text = "${trackedFood.carbs}g C",
                        colors = nutrientColors,
                        icon = R.drawable.ic_carbs,
                    )
                    ItemFoodBadge(
                        text = "${trackedFood.protein}g P",
                        colors = nutrientColors,
                        icon = R.drawable.ic_proteins,
                    )
                    ItemFoodBadge(
                        text = "${trackedFood.fat}g F",
                        colors = nutrientColors,
                        icon = R.drawable.ic_fats,
                    )
                }
            }
        }
    }
}


@Composable
@Preview()
fun ItemTrackedFoodPreview() {
    CalorieTrackerTheme {
        ItemTrackedFood(
            trackedFood = TrackedFood(
                name = "Manzana",
                carbs = 25,
                protein = 1,
                fat = 0,
                imageUrl = "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-vegetables.jpg",
                mealType = MealType.Breakfast,
                amount = 1,
                date = LocalDate.now(),
                calories = 95,
                id = 1,
            )
        )
    }
}
