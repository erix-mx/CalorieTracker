package com.erix.course.philipp.onboarding_presentation.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.model.ActivityLevel
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.onboarding_presentation.activity.events.ActivityEvent
import com.erix.course.philipp.onboarding_presentation.activity.state.ActivityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(
        ActivityState(
            activityLevel = preferences.loadUserInfo().activityLevel
        )
    )
    val state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun setActivity(activityLevel: ActivityLevel) {
        _state.update { it.copy(activityLevel = activityLevel) }
    }

    fun onNavigate(uiEvent: UiEvent) {
        viewModelScope.launch {
            when(uiEvent) {
                is UiEvent.NavigateTo -> preferences.saveActivityLevel(state.value.activityLevel)
                else -> Unit
            }
            _uiEvent.send(uiEvent)
        }
    }

    fun dispatchEvent(activityEvent: ActivityEvent) {
        when(activityEvent) {
            is ActivityEvent.OnSelectedActivity -> setActivity(activityEvent.activityLevel)
        }
    }
}