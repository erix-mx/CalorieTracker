package com.erix.course.philipp.calorytracker.extensions

import androidx.navigation.NavController
import com.erix.course.philipp.core_main.utils.UiEvent

fun NavController.navigateTo(UiEvent: UiEvent) {
    when (UiEvent) {
        is UiEvent.NavigateTo -> navigate(UiEvent.route)
        is UiEvent.NavigateBack -> popBackStack()
    }
}