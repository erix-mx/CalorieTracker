package com.erix.course.philipp.onboarding_presentation.nutrient

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.dialogs.PickerSheetDialog
import com.erix.course.philipp.core_ui.form.ItemBackground
import com.erix.course.philipp.core_ui.form.ValueIndicator
import com.erix.course.philipp.core_ui.text.H1
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.onboarding_presentation.R
import com.erix.course.philipp.onboarding_presentation.components.window.BasicScaffold
import com.erix.course.philipp.onboarding_presentation.nutrient.events.NutrientEvent
import com.erix.course.philipp.onboarding_presentation.nutrient.state.NutrientState
import com.erix.course.philipp.onboarding_presentation.nutrient.viewmodel.NutrientViewModel
import kotlinx.coroutines.launch


@Composable
fun NutrientScreen(
    viewModel: NutrientViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit
) {

    LaunchedEffect(key1 = true,) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }
    NutrientScreenContent(
        state = viewModel.state,
        nutrientEvent = viewModel::dispatcherEvent,
        onNavigate = viewModel::onNavigate
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NutrientScreenContent(
    state: NutrientState = NutrientState(),
    nutrientEvent: (NutrientEvent) -> Unit = {},
    onNavigate: (UiEvent) -> Unit
) {
    val dimensions = LocalSpacing.current

    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()
    val weights = (0..100 step 5).toList()

    PickerSheetDialog(
        title = state.currentTitlePicker,
        modalState = modalState,
        values = weights,
        unit = "%",
        color = MaterialTheme.colorScheme.onSecondary,
        sheetContentColor = MaterialTheme.colorScheme.surface,
        onValueSelected = {
            when(state.currentTitlePicker) {
                "Carbs" -> nutrientEvent(NutrientEvent.SelectCarbs(it.toFloat()))
                "Proteins" -> nutrientEvent(NutrientEvent.SelectProteins(it.toFloat()))
                "Fats" -> nutrientEvent(NutrientEvent.SelectFats(it.toFloat()))
            }
            scope.launch { modalState.hide() }
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
                    H1(
                        text = stringResource(id = R.string.nutrient_title),
                        color = MaterialTheme.colorScheme.onSecondary,
                        lineHeight = 48.sp
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                    Normal(
                        text = stringResource(id = R.string.nutrient_description),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                    ItemBackground(
                        colorBackground = MaterialTheme.colorScheme.surface,
                        colorIconBackground = MaterialTheme.colorScheme.onSurface,
                        icon = R.drawable.ic_pizza,
                        borderColor = MaterialTheme.colorScheme.primary,
                        tintIconColor = MaterialTheme.colorScheme.onSecondary,
                        click = {
                            nutrientEvent(NutrientEvent.SelectTitlePicker("Carbs"))
                            scope.launch { modalState.show() }
                        }
                    ) {
                        ValueIndicator(
                            color = MaterialTheme.colorScheme.onSecondary,
                            value = state.selectedCarbs.toInt().toString(),
                            unit = "Carbs",
                            rightTag = "%",
                            textAlign = TextAlign.Start
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                    ItemBackground(
                        colorBackground = MaterialTheme.colorScheme.surface,
                        colorIconBackground = MaterialTheme.colorScheme.onSurface,
                        icon = R.drawable.ic_meal,
                        borderColor = MaterialTheme.colorScheme.primary,
                        tintIconColor = MaterialTheme.colorScheme.onSecondary,
                        click = {
                            nutrientEvent(NutrientEvent.SelectTitlePicker("Proteins"))
                            scope.launch { modalState.show() }

                        }
                    ) {
                        ValueIndicator(
                            color = MaterialTheme.colorScheme.onSecondary,
                            value = state.selectedProteins.toInt().toString(),
                            unit = "Proteins",
                            rightTag = "%",
                            textAlign = TextAlign.Start
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                    ItemBackground(
                        colorBackground = MaterialTheme.colorScheme.surface,
                        colorIconBackground = MaterialTheme.colorScheme.onSurface,
                        icon = R.drawable.ic_burger,
                        borderColor = MaterialTheme.colorScheme.primary,
                        tintIconColor = MaterialTheme.colorScheme.onSecondary,
                        click = {
                            nutrientEvent(NutrientEvent.SelectTitlePicker("Fats"))
                            scope.launch { modalState.show() }
                        }
                    ) {
                        ValueIndicator(
                            color = MaterialTheme.colorScheme.onSecondary,
                            value = state.selectedFats.toInt().toString(),
                            unit = "Fats",
                            rightTag = "%",
                            textAlign = TextAlign.Start
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
                        onNavigate(UiEvent.NavigateTo(Route.TRACKER_OVERVIEW))
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun NutrientScreenContentPreview() {
    NutrientScreenContent(
        state = NutrientState(),
        onNavigate = {}
    )
}