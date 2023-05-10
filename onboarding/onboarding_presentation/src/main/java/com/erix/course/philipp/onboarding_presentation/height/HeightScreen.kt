package com.erix.course.philipp.onboarding_presentation.height

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
import com.erix.course.philipp.onboarding_presentation.components.window.BasicScaffold
import com.erix.course.philipp.onboarding_presentation.height.events.HeightEvent
import com.erix.course.philipp.onboarding_presentation.height.state.HeightState
import com.erix.course.philipp.onboarding_presentation.height.viewmodel.HeightViewModel
import kotlinx.coroutines.launch

@Composable
fun HeightScreen(
    viewModel: HeightViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, ) {
        viewModel.uiEvent.collect {
            viewModel.onNavigate(UiEvent.NavigateTo(Route.WEIGHT))
        }
    }

    HeightScreenContent(
        state = state,
        onHeightEvent = viewModel::dispatchEvent,
        onNavigate = onNavigate,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeightScreenContent(
    state: HeightState,
    onHeightEvent: (HeightEvent) -> Unit,
    onNavigate: (UiEvent) -> Unit,
) {

    val dimensions = LocalSpacing.current

    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()
    val heights = (40..210).toList()

    PickerSheetDialog(
        title = stringResource(id = R.string.height_title),
        modalState = modalState,
        values = heights,
        unit = "cm",
        color = MaterialTheme.colorScheme.onSecondary,
        sheetContentColor = MaterialTheme.colorScheme.surface,
        onValueSelected = {
            scope.launch { modalState.hide() }
            onHeightEvent(HeightEvent.OnHeightChanged(it))
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
                        text = stringResource(id = R.string.height_title),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                    Normal(
                        text = stringResource(id = R.string.height_description),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                    ItemBackground(
                        colorBackground = MaterialTheme.colorScheme.surface,
                        colorIconBackground = MaterialTheme.colorScheme.onSurface,
                        icon = R.drawable.ic_verticar_arrow,
                        tintIconColor = MaterialTheme.colorScheme.onSecondary,
                        click = {
                            scope.launch {
                                modalState.show()
                            }
                        }
                    ) {
                        ValueIndicator(
                            color = MaterialTheme.colorScheme.onSecondary,
                            value = state.selectedHeight.toString(),
                            unit = "cm"
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
                    ) { onNavigate(UiEvent.NavigateTo(Route.WEIGHT)) }
                }
            }
        }
    }


}


@Composable
@Preview
fun HeightScreenContentPreview() {
    HeightScreenContent(
        state = HeightState(),
        onHeightEvent = {},
        onNavigate = {}
    )
}