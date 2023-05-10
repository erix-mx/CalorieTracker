package com.erix.course.philipp.onboarding_presentation.goal.events

import com.erix.course.philipp.core_main.domain.model.GoalType

sealed class GoalEvent {
    class OnSelectedGoal(val goalType: GoalType) : GoalEvent()
}
