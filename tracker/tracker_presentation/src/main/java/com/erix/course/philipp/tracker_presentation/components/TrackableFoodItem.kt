package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.theme.ItemTrackedFoodColors
import com.erix.course.philipp.tracker_presentation.search.state.TrackableFoodUiState
import com.erix.models.tracker.TrackableFood

@Composable
fun TrackableFoodItem(
    modifier: Modifier = Modifier,
    trackableFoodUiState: TrackableFoodUiState,
    onClick: (TrackableFood) -> Unit = {},
    onAmountChange: (String) -> Unit = {},
    onTrack: () -> Unit = {},
) {

    val food = trackableFoodUiState.food

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(29.dp))
            .clickable { onClick(food) }
            .background(color = MaterialTheme.colorScheme.background)
            .padding(14.dp)

    ) {
        ItemTrackedFood(
            colors = ItemTrackedFoodColors(
                text = MaterialTheme.colorScheme.onSecondary,
                background = Color.Transparent,
            ),
            trackedFood = food.toItem(),
        )
    }
}


@Preview
@Composable
fun TrackableFoodItemPreview() {
    TrackableFoodItem(
        trackableFoodUiState = TrackableFoodUiState(
            food = TrackableFood(
                name = "Manzana",
                imageUrl = "https://example.com/apple.jpg",
                caloriesPer100g = 52,
                carbsPer100g = 14,
                proteinPer100g = 0,
                fatPer100g = 0
            )
        ),
        onClick = {},
        onAmountChange = {},
        onTrack = {}
    )
}