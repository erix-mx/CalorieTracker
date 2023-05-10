package com.erix.course.philipp.onboarding_presentation.gender

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
import androidx.lifecycle.ViewModel
import com.erix.course.philipp.core_main.domain.model.Gender
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_main.utils.loge
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.form.ItemBackground
import com.erix.course.philipp.core_ui.form.ValueSimpleIndicator
import com.erix.course.philipp.core_ui.text.H1
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.onboarding_presentation.R
import com.erix.course.philipp.onboarding_presentation.components.window.BasicScaffold
import com.erix.course.philipp.onboarding_presentation.gender.events.GenderEvent
import com.erix.course.philipp.onboarding_presentation.gender.state.GenderState
import com.erix.course.philipp.onboarding_presentation.gender.viewmodel.GenderViewModel


@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true,) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }

    GenderScreenContent(
        state = state,
        onEvent = viewModel::dispatchEvent,
        onNavigate = viewModel::onNavigate,
    )
}

@Composable
fun GenderScreenContent(
    state: GenderState = GenderState(),
    onEvent: (GenderEvent) -> Unit = {},
    onNavigate: (UiEvent) -> Unit,
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
                    text = stringResource(id = R.string.gender_title),
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                Normal(
                    text = stringResource(id = R.string.gender_description),
                    color = MaterialTheme.colorScheme.onSecondary,
                )
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    icon = R.drawable.ic_man,
                    borderColor = MaterialTheme.colorScheme.primary,
                    selected = (state.selectedGender is Gender.Male),
                    click = {
                        onEvent(GenderEvent.OnSelectedGender(Gender.Male))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Male",
                    )
                }
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                ItemBackground(
                    colorBackground = MaterialTheme.colorScheme.surface,
                    colorIconBackground = MaterialTheme.colorScheme.onSurface,
                    tintIconColor = MaterialTheme.colorScheme.onSecondary,
                    icon = R.drawable.ic_woman,
                    borderColor = MaterialTheme.colorScheme.primary,
                    selected = (state.selectedGender is Gender.Female),
                    click = {
                        onEvent(GenderEvent.OnSelectedGender(Gender.Female))
                    }
                ) {
                    ValueSimpleIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        value = "Female",
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
                ) {
                    onNavigate(UiEvent.NavigateTo(Route.AGE))
                }
            }
        }
    }
}

@Composable
@Preview
fun GenderScreenContentPreview() {
    GenderScreenContent() {}
}