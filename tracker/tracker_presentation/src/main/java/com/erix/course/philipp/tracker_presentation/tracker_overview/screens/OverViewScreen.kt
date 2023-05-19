package com.erix.course.philipp.tracker_presentation.tracker_overview.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.colors.ColorsButton
import com.erix.course.philipp.tracker_presentation.components.DaySelector
import com.erix.course.philipp.tracker_presentation.components.NutrientsHeader
import com.erix.course.philipp.tracker_presentation.tracker_overview.event.TrackerOverviewEvent
import com.erix.course.philipp.tracker_presentation.tracker_overview.state.TrackerOverviewState
import com.erix.course.philipp.tracker_presentation.tracker_overview.viewmodel.TrackerOverviewViewModel

@Composable
fun OverViewScreen(
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val onEvent = viewModel::onEvent
    OverViewScreeContent(
        state = state,
        onEvent = onEvent
    )
}

@Composable
fun OverViewScreeContent(
    state: TrackerOverviewState = TrackerOverviewState(),
    onEvent: (TrackerOverviewEvent) -> Unit = {},
) {
    val spacing = LocalSpacing.current

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
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
    }
}


@Composable
@Preview
fun SearchFoodScreenPreview() {
    OverViewScreeContent()
}


