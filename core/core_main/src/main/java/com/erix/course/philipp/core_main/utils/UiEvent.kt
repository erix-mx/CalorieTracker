package com.erix.course.philipp.core_main.utils

sealed class UiEvent {
    class NavigateTo(val route: String): UiEvent()
    object NavigateBack: UiEvent()
}
