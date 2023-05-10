package com.erix.course.philipp.onboarding_presentation.weight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.onboarding_presentation.weight.events.WeightEvent
import com.erix.course.philipp.onboarding_presentation.weight.state.WeightState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {

    private val _state = MutableStateFlow(WeightState(selectedWeight = preferences.loadUserInfo().weight))
    val state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNavigate(UiEvent: UiEvent) {
        viewModelScope.launch {
            preferences.saveWeight(state.value.selectedWeight)
            _uiEvent.send(UiEvent)
        }
    }

    fun dispatchEvent(event: WeightEvent) {
        when (event) {
            is WeightEvent.OnSelectWeight -> _state.value = state.value.copy(selectedWeight = event.weight)
        }
    }

}