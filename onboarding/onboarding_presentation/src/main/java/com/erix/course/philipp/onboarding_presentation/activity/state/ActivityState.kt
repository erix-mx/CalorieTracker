package com.erix.course.philipp.onboarding_presentation.activity.state

import com.erix.course.philipp.core_main.domain.model.ActivityLevel

data class ActivityState(
    val activityLevel: ActivityLevel = ActivityLevel.Medium
)