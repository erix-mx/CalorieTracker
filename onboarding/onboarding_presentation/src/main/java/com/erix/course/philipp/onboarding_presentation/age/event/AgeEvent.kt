package com.erix.course.philipp.onboarding_presentation.age.event

sealed class AgeEvent {
    class OnSelectAge(val age: Int) : AgeEvent()
    fun asString(): String {
        return when (this) {
            is OnSelectAge -> age.toString()
        }
    }
}