package com.erix.course.philipp.onboarding_presentation.goal

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
import com.erix.course.philipp.core_main.domain.model.GoalType
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.form.ItemBackground
import com.erix.course.philipp.core_ui.form.ValueSimpleIndicator
import com.erix.course.philipp.core_ui.text.H1
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.onboarding_presentation.R
import com.erix.course.philipp.onboarding_presentation.components.window.BasicScaffold
import com.erix.course.philipp.onboarding_presentation.goal.events.GoalEvent
import com.erix.course.philipp.onboarding_presentation.goal.state.GoalState
import com.erix.course.philipp.onboarding_presentation.goal.viewmodel.GoalViewModel

@Composable
fun GoalScreen(
    viewModel: GoalViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true,) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }

    GoalScreenContent(
        state = state,
        goalEvent = viewModel::dispatchEvent,
        onNavigate = viewModel::onNavigate
    )

}

@Composable
fun GoalScreenContent(
    state: GoalState,
    goalEvent: (GoalEvent) -> Unit,
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
                    text = stringResource(id = R.string.goal_title),
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                Normal(
                    text = stringResource(id = R.string.goal_description),
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    icon = R.drawable.ic_body_gain,
                    borderColor = MaterialTheme.colorScheme.primary,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    selected = state.selectedGoal is GoalType.GainWeight,
                    click = {
                        goalEvent(GoalEvent.OnSelectedGoal(GoalType.GainWeight))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Gain",
                    )
                }
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    icon = R.drawable.ic_body_keep,
                    borderColor = MaterialTheme.colorScheme.primary,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    selected = state.selectedGoal is GoalType.KeepWeight,
                    click = {
                        goalEvent(GoalEvent.OnSelectedGoal(GoalType.KeepWeight))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Keep",
                    )
                }
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    icon = R.drawable.ic_body_lose,
                    borderColor = MaterialTheme.colorScheme.primary,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    selected = state.selectedGoal is GoalType.LoseWeight,
                    click = {
                        goalEvent(GoalEvent.OnSelectedGoal(GoalType.LoseWeight))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Lose",
                    )
                }
            }


            Box(modifier = Modifier
                .fillMaxWidth()
                .height(dimensions.spaceExtraLargest)
            ){
                BigButton(
                    text = stringResource(id = R.string.tag_next),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.secondary,
                ) { onNavigate(UiEvent.NavigateTo(Route.NUTRIENT_GOAL)) }
            }
        }
    }
}

@Composable
@Preview
fun GoalScreenContentPreview() {
    GoalScreenContent(
        state = GoalState(),
        goalEvent = {},
        onNavigate = {}
    )
}