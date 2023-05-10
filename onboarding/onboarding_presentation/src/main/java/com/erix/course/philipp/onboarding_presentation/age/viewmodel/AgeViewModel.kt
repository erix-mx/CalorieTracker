package com.erix.course.philipp.onboarding_presentation.age.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.onboarding_presentation.age.event.AgeEvent
import com.erix.course.philipp.onboarding_presentation.age.state.AgeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
): ViewModel() {

    private val _state = MutableStateFlow(AgeState(selectAge = preferences.loadUserInfo().age))
    val state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNavigate(UiEvent: UiEvent) {
        viewModelScope.launch {
            preferences.saveAge(state.value.selectAge)
            _uiEvent.send(UiEvent)
        }
    }

    fun dispatchEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnSelectAge -> _state.value = state.value.copy(selectAge = event.age)
        }
    }
}