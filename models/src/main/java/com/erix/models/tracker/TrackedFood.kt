package com.erix.models.tracker

import java.time.LocalDate

data class TrackedFood(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType = MealType.Breakfast,
    val amount: Int,
    val date: LocalDate = LocalDate.now(),
    val calories: Int,
    val id: Int? = null
)