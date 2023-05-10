package com.erix.course.philipp.onboarding_presentation.goal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.onboarding_presentation.goal.events.GoalEvent
import com.erix.course.philipp.onboarding_presentation.goal.state.GoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _state = MutableStateFlow(GoalState(selectedGoal = preferences.loadUserInfo().goalType))
    val state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun dispatchEvent(event: GoalEvent) {
        when (event) {
            is GoalEvent.OnSelectedGoal -> {
                _state.value = _state.value.copy(selectedGoal = event.goalType)
            }
        }
    }

    fun onNavigate(event: UiEvent) {
        viewModelScope.launch {
            preferences.saveGoalType(_state.value.selectedGoal)
            _uiEvent.send(event)
        }
    }

}
