package com.erix.course.philipp.onboarding_presentation.gender.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erix.course.philipp.core_main.domain.model.Gender
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.onboarding_presentation.gender.events.GenderEvent
import com.erix.course.philipp.onboarding_presentation.gender.state.GenderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    private val _state = MutableStateFlow(GenderState(selectedGender = preferences.loadUserInfo().gender))
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun setGender(gender: Gender) {
        _state.update{ it.copy(selectedGender = gender) }
    }

    fun onNavigate(uiEvent: UiEvent) {
        viewModelScope.launch {
            when(uiEvent) {
                is UiEvent.NavigateTo -> preferences.saveGender(state.value.selectedGender)
                else -> Unit
            }
            _uiEvent.send(uiEvent)
        }
    }


    fun dispatchEvent(genderEvent: GenderEvent) {
        when(genderEvent) {
            is GenderEvent.OnSelectedGender -> {
                setGender(genderEvent.gender)
            }
        }
    }
}