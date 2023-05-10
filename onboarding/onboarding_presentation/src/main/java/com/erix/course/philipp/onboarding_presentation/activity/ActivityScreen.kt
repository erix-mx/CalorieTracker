package com.erix.course.philipp.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.erix.course.philipp.core_main.domain.model.ActivityLevel
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.form.ItemBackground
import com.erix.course.philipp.core_ui.form.ValueSimpleIndicator
import com.erix.course.philipp.core_ui.text.H1
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.onboarding_presentation.R
import com.erix.course.philipp.onboarding_presentation.activity.events.ActivityEvent
import com.erix.course.philipp.onboarding_presentation.activity.state.ActivityState
import com.erix.course.philipp.onboarding_presentation.activity.viewmodel.ActivityViewModel
import com.erix.course.philipp.onboarding_presentation.components.window.BasicScaffold

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = true, ) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }

    ActivityScreenContent(
        state = state,
        activityEvent = viewModel::dispatchEvent,
        onNavigate = viewModel::onNavigate,
    )

}


@Composable
fun ActivityScreenContent(
    state: ActivityState,
    activityEvent: (ActivityEvent) -> Unit,
    onNavigate: (UiEvent) -> Unit
) {
    val dimensions = LocalSpacing.current
    BasicScaffold(onNavigate = onNavigate) {
        Column(
            modifier = Modifier.padding(horizontal = dimensions.horizontalGlobalPadding)
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                H1(
                    text = stringResource(id = R.string.activty_title),
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                Normal(
                    text = stringResource(id = R.string.activity_description),
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    icon = R.drawable.ic_high_battery,
                    borderColor = MaterialTheme.colorScheme.primary,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    selected = (state.activityLevel == ActivityLevel.High),
                    click = {
                        activityEvent(ActivityEvent.OnSelectedActivity(activityLevel = ActivityLevel.High))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "High",
                    )
                }
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    icon = R.drawable.ic_medium_battery,
                    borderColor = MaterialTheme.colorScheme.primary,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    selected = (state.activityLevel == ActivityLevel.Medium),
                    click = {
                        activityEvent(ActivityEvent.OnSelectedActivity(activityLevel = ActivityLevel.Medium))
                    }

                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Medium",
                    )
                }
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    icon = R.drawable.ic_low_battery,
                    borderColor = MaterialTheme.colorScheme.primary,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    selected = (state.activityLevel == ActivityLevel.Low),
                    click = {
                        activityEvent(ActivityEvent.OnSelectedActivity(activityLevel = ActivityLevel.Low))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Low",
                    )
                }
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensions.spaceExtraLargest)
            ) {
                BigButton(
                    text = stringResource(id = R.string.tag_next),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.secondary,
                ) { onNavigate(UiEvent.NavigateTo(Route.GOAL)) }
            }
        }
    }
}

@Composable
@Preview
fun ActivityScreenContentPreview() {
    ActivityScreenContent(
        onNavigate = {},
        state = ActivityState(),
        activityEvent = {},
    )
}