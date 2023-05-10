package com.erix.course.philipp.onboarding_presentation.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.dialogs.PickerSheetDialog
import com.erix.course.philipp.core_ui.form.ItemBackground
import com.erix.course.philipp.core_ui.form.ValueIndicator
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.onboarding_presentation.R
import com.erix.course.philipp.onboarding_presentation.age.event.AgeEvent
import com.erix.course.philipp.onboarding_presentation.components.window.BasicScaffold
import com.erix.course.philipp.onboarding_presentation.weight.events.WeightEvent
import com.erix.course.philipp.onboarding_presentation.weight.state.WeightState
import com.erix.course.philipp.onboarding_presentation.weight.viewmodel.WeightViewModel
import kotlinx.coroutines.launch


@Composable
fun WeightScreen(
    viewModel: WeightViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, ) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }

    WeightScreenContent(
        state = state,
        onWeightEvent = viewModel::dispatchEvent,
        onNavigate = viewModel::onNavigate
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeightScreenContent(
    state: WeightState,
    onWeightEvent: (WeightEvent) -> Unit,
    onNavigate: (UiEvent) -> Unit
) {
    val dimensions = LocalSpacing.current

    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()
    val weights = (40..200).toList()

    PickerSheetDialog(
        title = stringResource(id = R.string.weight_title),
        modalState = modalState,
        values = weights,
        unit = "Kg",
        color = MaterialTheme.colorScheme.onSecondary,
        sheetContentColor = MaterialTheme.colorScheme.surface,
        onValueSelected = {
            scope.launch { modalState.hide() }
            onWeightEvent(WeightEvent.OnSelectWeight(it.toFloat()))
        }
    ) {
        BasicScaffold(onNavigate = onNavigate) {
            Column(
                modifier = Modifier.padding(horizontal = dimensions.horizontalGlobalPadding)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    H3(
                        text = stringResource(id = R.string.weight_title),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                    Normal(
                        text = stringResource(id = R.string.weight_description),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                    ItemBackground(
                        colorBackground = MaterialTheme.colorScheme.surface,
                        colorIconBackground = MaterialTheme.colorScheme.onSurface,
                        icon = R.drawable.ic_weight,
                        tintIconColor = MaterialTheme.colorScheme.onSecondary,
                        click = {
                            scope.launch { modalState.show() }
                        }
                    ) {
                        ValueIndicator(
                            color = MaterialTheme.colorScheme.onSecondary,
                            value = state.selectedWeight.toString(),
                            unit = "Kg"
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
                    ) { onNavigate(UiEvent.NavigateTo(Route.ACTIVITY)) }
                }
            }
        }
    }

}

@Composable
@Preview
fun WeightScreenContentPreview() {
    WeightScreenContent(
        onNavigate = {},
        state = WeightState(),
        onWeightEvent = {}
    )
}