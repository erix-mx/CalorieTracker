package com.erix.course.philipp.onboarding_presentation.weight.events

sealed class WeightEvent {
    data class OnSelectWeight(val weight: Float): WeightEvent()
}
