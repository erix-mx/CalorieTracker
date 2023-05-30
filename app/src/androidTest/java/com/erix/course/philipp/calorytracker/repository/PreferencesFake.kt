package com.erix.course.philipp.calorytracker.repository

import com.erix.course.philipp.core_main.domain.model.ActivityLevel
import com.erix.course.philipp.core_main.domain.model.Gender
import com.erix.course.philipp.core_main.domain.model.GoalType
import com.erix.course.philipp.core_main.domain.model.UserInfo
import com.erix.course.philipp.core_main.domain.preferences.Preferences

class PreferencesFake: Preferences {
    override fun saveGender(gender: Gender) {
        TODO("Not yet implemented")
    }

    override fun saveAge(age: Int) {
        TODO("Not yet implemented")
    }

    override fun saveWeight(weight: Float) {
        TODO("Not yet implemented")
    }

    override fun saveHeight(height: Int) {
        TODO("Not yet implemented")
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        TODO("Not yet implemented")
    }

    override fun saveGoalType(type: GoalType) {
        TODO("Not yet implemented")
    }

    override fun saveCarbRatio(ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun saveProteinRatio(ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun saveFatRatio(ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun loadUserInfo(): UserInfo {
        return UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        // do nothing
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return false
    }

}