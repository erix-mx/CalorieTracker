package com.erix.course.philipp.calorytracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.erix.course.philipp.calorytracker.state.MainState
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    preferences: Preferences,
) : ViewModel() {


    var state by mutableStateOf(MainState(showOnBoarding = preferences.loadShouldShowOnboarding()))
        private set
}