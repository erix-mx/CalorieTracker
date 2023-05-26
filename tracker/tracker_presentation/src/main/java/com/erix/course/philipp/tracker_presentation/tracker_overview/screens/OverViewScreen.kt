package com.erix.course.philipp.tracker_presentation.tracker_overview.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButtonAdd
import com.erix.course.philipp.core_ui.colors.ColorsButton
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.core_ui.theme.ItemBackgroundColors
import com.erix.course.philipp.tracker_presentation.components.ExpandableMeal
import com.erix.course.philipp.core_ui.theme.ItemInfoGramsColors
import com.erix.course.philipp.core_ui.theme.ItemTrackedFoodColors
import com.erix.course.philipp.tracker_presentation.R
import com.erix.course.philipp.tracker_presentation.components.DaySelector
import com.erix.course.philipp.tracker_presentation.components.ItemTrackedFood
import com.erix.course.philipp.tracker_presentation.components.NutrientsHeader
import com.erix.course.philipp.tracker_presentation.tracker_overview.event.TrackerOverviewEvent
import com.erix.course.philipp.tracker_presentation.tracker_overview.state.TrackerOverviewState
import com.erix.course.philipp.tracker_presentation.tracker_overview.viewmodel.TrackerOverviewViewModel
import com.erix.models.tracker.MealType
import com.erix.models.tracker.TrackedFood
import java.time.LocalDate

@Composable
fun OverViewScreen(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit = {},
) {

    LaunchedEffect(key1 = true, ) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }

    OverViewScreeContent(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun OverViewScreeContent(
    state: TrackerOverviewState = TrackerOverviewState(),
    onEvent: (TrackerOverviewEvent) -> Unit = {},
) {
    val spacing = LocalSpacing.current

    val infoGramsColors = ItemInfoGramsColors(
        text = MaterialTheme.colorScheme.onSecondary,
        background = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.2f),
    )

    val itemBackgroundColors = ItemBackgroundColors(
        colorBackground = MaterialTheme.colorScheme.background,
        colorIconBackground = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.2f),
        tintIconColor = MaterialTheme.colorScheme.onSecondary,
        borderColor = MaterialTheme.colorScheme.onSecondary
    )

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(
                state = state,
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                colors = ColorsButton(
                    background = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                    text = Color.White
                ),
                color = MaterialTheme.colorScheme.onSecondary,
                date = state.date,
                onPreviousDayClick = {
                    onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
        }
        items(state.meals) { meal ->
            Column(modifier = Modifier.padding(horizontal = spacing.spaceMedium)) {
                ExpandableMeal(
                    itemBackgroundColors = itemBackgroundColors,
                    tagColor = MaterialTheme.colorScheme.onSecondary,
                    infoGramsColors = infoGramsColors,
                    meal = meal,
                    onToggleClick = {
                        onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                    },
                    content = {
                        val foods = state.trackedFoods.filter { it.mealType == meal.mealType }
                        Column {
                            foods.forEach { food ->
                                ItemTrackedFood(
                                    onDelete = {
                                        onEvent(TrackerOverviewEvent.OnDeleteTrackedFoodClick(food))
                                    },
                                    colors = ItemTrackedFoodColors(
                                        text = MaterialTheme.colorScheme.onSecondary,
                                        background = Color.Transparent,
                                    ),
                                    trackedFood = food
                                )
                                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                            }
                        }

                        Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        BigButtonAdd(
                            borderColor = MaterialTheme.colorScheme.primary,
                            textColor = MaterialTheme.colorScheme.primary,
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                onEvent(TrackerOverviewEvent.OnAddFoodClick(meal))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }
        }
    }
}


@Composable
@Preview
fun SearchFoodScreenPreview() {
    CalorieTrackerTheme {
        OverViewScreeContent()
    }
}


