package com.erix.course.philipp.onboarding_presentation.height.events

sealed class HeightEvent {
    class OnHeightChanged(val height: Int) : HeightEvent()
}