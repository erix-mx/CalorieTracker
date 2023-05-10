package com.erix.course.philipp.onboarding_presentation.height.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.onboarding_presentation.height.events.HeightEvent
import com.erix.course.philipp.onboarding_presentation.height.state.HeightState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _state = MutableStateFlow(HeightState(preferences.loadUserInfo().height))
    val state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNavigate(uiEvent: UiEvent) {
        viewModelScope.launch {
            preferences.saveHeight(state.value.selectedHeight)
            _uiEvent.send(uiEvent)
        }
    }

    fun dispatchEvent(heightEvent: HeightEvent) {
        when (heightEvent) {
            is HeightEvent.OnHeightChanged -> _state.value = HeightState(selectedHeight = heightEvent.height)
        }
    }

}
