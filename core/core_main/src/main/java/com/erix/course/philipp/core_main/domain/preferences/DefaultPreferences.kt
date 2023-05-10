package com.erix.course.philipp.core_main.domain.preferences

import android.content.SharedPreferences
import com.erix.course.philipp.core_main.domain.model.ActivityLevel
import com.erix.course.philipp.core_main.domain.model.Gender
import com.erix.course.philipp.core_main.domain.model.GoalType
import com.erix.course.philipp.core_main.domain.model.UserInfo
import com.erix.course.philipp.core_main.extensions.get
import com.erix.course.philipp.core_main.extensions.put

class DefaultPreferences(
    private val prefs: SharedPreferences
): Preferences {
    override fun saveGender(gender: Gender) {
        prefs.put(Preferences.KEY_GENDER, gender.name)
    }

    override fun saveAge(age: Int) {
        prefs.put(Preferences.KEY_AGE, age)
    }

    override fun saveWeight(weight: Float) {
        prefs.put(Preferences.KEY_WEIGHT, weight)
    }

    override fun saveHeight(height: Int) {
        prefs.put(Preferences.KEY_HEIGHT, height)
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        prefs.put(Preferences.KEY_ACTIVITY_LEVEL, level.name)
    }

    override fun saveGoalType(type: GoalType) {
        prefs.put(Preferences.KEY_GOAL_TYPE, type.name)
    }

    override fun saveCarbRatio(ratio: Float) {
        prefs.put(Preferences.KEY_CARB_RATIO, ratio)
    }

    override fun saveProteinRatio(ratio: Float) {
        prefs.put(Preferences.KEY_PROTEIN_RATIO, ratio)
    }

    override fun saveFatRatio(ratio: Float) {
        prefs.put(Preferences.KEY_FAT_RATIO, ratio)
    }

    override fun loadUserInfo(): UserInfo {
        val age = prefs.get(Preferences.KEY_AGE, 18)
        val height = prefs.get(Preferences.KEY_HEIGHT, 180)
        val weight = prefs.get(Preferences.KEY_WEIGHT, 65f)
        val genderString = prefs.get(Preferences.KEY_GENDER, "")
        val activityLevelString = prefs.get(Preferences.KEY_ACTIVITY_LEVEL, "")
        val goalType = prefs.get(Preferences.KEY_GOAL_TYPE, "")
        val carbRatio = prefs.get(Preferences.KEY_CARB_RATIO, 20f)
        val proteinRatio = prefs.get(Preferences.KEY_PROTEIN_RATIO, 50f)
        val fatRatio = prefs.get(Preferences.KEY_FAT_RATIO, 60f)

        return UserInfo(
            gender = Gender.fromString(genderString),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString),
            goalType = GoalType.fromString(goalType),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )

    }

}