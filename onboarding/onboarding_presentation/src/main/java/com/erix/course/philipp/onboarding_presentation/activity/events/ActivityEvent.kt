package com.erix.course.philipp.onboarding_presentation.activity.events

import com.erix.course.philipp.core_main.domain.model.ActivityLevel

sealed class ActivityEvent {
    data class OnSelectedActivity(val activityLevel: ActivityLevel) : ActivityEvent()
}
