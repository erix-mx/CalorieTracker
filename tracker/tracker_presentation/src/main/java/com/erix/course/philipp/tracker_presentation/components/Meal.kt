package com.erix.course.philipp.tracker_presentation.components

import androidx.annotation.DrawableRes
import com.erix.course.philipp.core_main.utils.UiText
import com.erix.course.philipp.tracker_presentation.R
import com.erix.models.tracker.MealType

data class Meal(
    val name: UiText = UiText.DynamicString("Breakfast"),
    @DrawableRes val drawableRes: Int = R.drawable.ic_breakfast,
    val mealType: MealType = MealType.Breakfast,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

