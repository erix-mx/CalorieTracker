package com.erix.course.philipp.tracker_presentation.search.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.tracker_domain.usescase.TrackerUseCases
import com.erix.course.philipp.tracker_presentation.search.events.SearchEvent
import com.erix.course.philipp.tracker_presentation.search.state.SearchState
import com.erix.course.philipp.tracker_presentation.search.state.TrackableFoodUiState
import com.erix.models.tracker.MealType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
): ViewModel() {

    private var searchJob: Job? = null

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNavigate(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun dispatcherEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
                executeSearch()
            }

            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(
                    trackableFood = state.trackableFood.map {
                        if(it.food == event.food) {
                            it.copy(amount = event.amount )
                        } else it
                    }
                )
            }
            SearchEvent.OnSearch -> {
                if (state.query.isNotEmpty()) {
                    executeSearch()
                }
            }
            is SearchEvent.OnSearchFocusChange -> TODO()
            is SearchEvent.OnToggleTrackableFood -> {

                state = state.copy(
                    trackableFood = state.trackableFood.map {
                        if(it.food == event.food) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }
            is SearchEvent.OnTrackFoodClick -> TODO()
            SearchEvent.ClearQuery -> state = state.copy(query = "")
            is SearchEvent.OnCurrentFood -> state = state.copy(currentFood = event.food)
            is SearchEvent.OnSelectedValueAmount -> state =state.copy(selectedValueAmount = event.amount)

            is SearchEvent.OnAddFoodClick -> addFood(event)
        }
    }

    private fun addFood(event: SearchEvent.OnAddFoodClick) {
        viewModelScope.launch(Dispatchers.IO) {
            val food = state.currentFood ?: return@launch
            val amount = state.selectedValueAmount
            val date = event.date
            val mealType =  MealType.fromString(event.mealTypeName)

            trackerUseCases
                .trackFood(
                    food = food,
                    amount = amount,
                    date = date,
                    mealType = mealType
                )
            Log.e("SearchViewModel", "🔥addFood: $food")
        }
    }

    private fun executeSearch(typeDelay: Long = 1_000L){
        state = state.copy(isSearching = true, hasError = false)
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(typeDelay)

            trackerUseCases
                .searchFood(state.query)
                .onSuccess { foods ->
                    state = state.copy(
                        trackableFood = foods.map {

                            TrackableFoodUiState(it)
                        },
                        isSearching = false,
                    )
                }
                .onFailure {
                    state = state.copy(isSearching = false, hasError = true)
                }
        }
    }

}