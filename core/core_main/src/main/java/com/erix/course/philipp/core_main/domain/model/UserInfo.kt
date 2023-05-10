package com.erix.course.philipp.core_main.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Int = 180,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)