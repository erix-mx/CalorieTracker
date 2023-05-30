package com.erix.course.philipp.tracker_presentation.search.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.bars.SearchBar
import com.erix.course.philipp.core_ui.dialogs.PickerCheckSheetDialog
import com.erix.course.philipp.core_ui.loaders.Loader
import com.erix.course.philipp.core_ui.spacers.BoxSpacer
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.core_ui.theme.SearchBarColors
import com.erix.course.philipp.tracker_presentation.R
import com.erix.course.philipp.tracker_presentation.components.TrackableFoodItem
import com.erix.course.philipp.tracker_presentation.search.components.WindowPlaceholder
import com.erix.course.philipp.tracker_presentation.search.events.SearchEvent
import com.erix.course.philipp.tracker_presentation.search.state.SearchState
import com.erix.course.philipp.tracker_presentation.search.viewmodel.SearchViewModel
import com.erix.models.tracker.SearchArguments
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun SearchScreen(
    searchArguments: SearchArguments = SearchArguments(),
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (UiEvent) -> Unit
) {

    LaunchedEffect(key1 = Unit, ) {
        viewModel.uiEvent.collect {
            onNavigate(it)
        }
    }

    SearchScreenContent(
        state = viewModel.state,
        onEvent = viewModel::dispatcherEvent,
        searchArguments = searchArguments,
        onNavigate = viewModel::onNavigate
    )
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SearchScreenContent(
    searchArguments: SearchArguments = SearchArguments(),
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    onNavigate: (UiEvent) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val isSearching by remember(state.isSearching) { mutableStateOf(state.isSearching) }

    val searchBarColors = SearchBarColors(
        background = MaterialTheme.colorScheme.background,
        backgroundBox = MaterialTheme.colorScheme.onBackground,
        text = MaterialTheme.colorScheme.onSecondary,
    )

    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()
    val grams = (10..1000 step 5).toList()

    PickerCheckSheetDialog(
        title = state.currentFood?.name ?: "",
        modalState = modalState,
        values = grams,
        unit = "g",
        color = MaterialTheme.colorScheme.onSecondary,
        sheetContentColor = MaterialTheme.colorScheme.surface,
        selectedValueAmount = state.selectedValueAmount,
        click = {
            onEvent(
                SearchEvent.OnAddFoodClick(
                    mealTypeName = searchArguments.mealName,
                    date = LocalDate.of(
                        searchArguments.year,
                        searchArguments.month,
                        searchArguments.dayOfMonth
                    ),
                )
            )
            scope.launch { modalState.hide() }
        },
        onValueSelected = {
            onEvent(SearchEvent.OnSelectedValueAmount(it))
        }
    ) {
        val spacing = LocalSpacing.current

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                SearchBar(
                    colors = searchBarColors,
                    title = "Add ${searchArguments.mealName}",
                    query = state.query,
                    onQueryChange = { onEvent(SearchEvent.OnQueryChange(it)) },
                    onClear = { onEvent(SearchEvent.ClearQuery) },
                    onSearch = { onEvent(SearchEvent.OnSearch) },
                    onBack = { onNavigate(UiEvent.NavigateTo(Route.TRACKER_OVERVIEW)) },
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {

                if (state.query.isEmpty()) {
                    WindowPlaceholder(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
                        caption = "Search for food to add to your ${searchArguments.mealName}",
                        icon = R.drawable.ic_breakfast
                    )
                } else if (isSearching) {
                    Loader(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f)
                    )
                } else if (state.trackableFood.isEmpty()) {
                    WindowPlaceholder(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
                        caption = stringResource(id = R.string.no_results_found),
                        blackText = state.query,
                        icon = R.drawable.ic_square_search
                    )
                } else if (state.hasError) {
                    WindowPlaceholder(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
                        caption = stringResource(id = R.string.error_occurred),
                        icon = R.drawable.ic_warning
                    )
                } else {
                    keyboardController?.hide()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = spacing.spaceMedium),
                    ) {
                        item { BoxSpacer(spacing.spaceMedium) }
                        items(state.trackableFood.toList()) { food ->
                            TrackableFoodItem(
                                trackableFoodUiState = food,
                                onClick = {
                                    onEvent(SearchEvent.OnCurrentFood(food.food))
                                    scope.launch { modalState.show() }
                                }
                            )
                            BoxSpacer(spacing.spaceMedium)
                        }
                        item { BoxSpacer(spacing.spaceMedium) }
                    }
                }


            }
        }
    }


}

@Composable
@Preview
fun SearchScreenPreview() {
    CalorieTrackerTheme {
        SearchScreenContent(
            state = SearchState(),
            onEvent = {},
            searchArguments = SearchArguments(
                mealName = "Breakfast",
            ),
        )
    }
}

