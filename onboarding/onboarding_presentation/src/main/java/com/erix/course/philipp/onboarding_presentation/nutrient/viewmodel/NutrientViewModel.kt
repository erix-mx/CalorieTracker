package com.erix.course.philipp.onboarding_presentation.nutrient.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_main.utils.loge
import com.erix.course.philipp.onboarding_presentation.nutrient.events.NutrientEvent
import com.erix.course.philipp.onboarding_presentation.nutrient.state.NutrientState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientViewModel @Inject constructor(
    val preferences: Preferences
): ViewModel() {

    var state by mutableStateOf(NutrientState(
        selectedCarbs = preferences.loadUserInfo().carbRatio,
        selectedProteins = preferences.loadUserInfo().proteinRatio,
        selectedFats = preferences.loadUserInfo().fatRatio,
    ))
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun dispatcherEvent(NutrientEvent: NutrientEvent){
        when(NutrientEvent){
            is NutrientEvent.SelectCarbs -> {
                state = state.copy(selectedCarbs = NutrientEvent.carbs)
            }
            is NutrientEvent.SelectProteins -> {
                state = state.copy(selectedProteins = NutrientEvent.proteins)
            }
            is NutrientEvent.SelectFats -> {
                state = state.copy(selectedFats = NutrientEvent.fats)
            }
            is NutrientEvent.SelectTitlePicker -> {
                state = state.copy(currentTitlePicker = NutrientEvent.title)
            }
            else -> Unit
        }
    }

    fun onNavigate(uiEvent: UiEvent){
        viewModelScope.launch {
            loge { "onNavigate: $state" }
            preferences.saveCarbRatio(state.selectedCarbs)
            preferences.saveProteinRatio(state.selectedProteins)
            preferences.saveFatRatio(state.selectedFats)
            _uiEvent.send(uiEvent)
        }
    }

}