package com.erix.models.tracker

import androidx.annotation.DrawableRes
import com.erix.course.philipp.core_main.utils.UiText

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)