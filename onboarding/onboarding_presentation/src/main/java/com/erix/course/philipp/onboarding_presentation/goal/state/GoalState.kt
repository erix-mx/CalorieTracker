package com.erix.course.philipp.onboarding_presentation.goal.state

import com.erix.course.philipp.core_main.domain.model.GoalType

data class GoalState(
    val selectedGoal: GoalType = GoalType.KeepWeight,
)
