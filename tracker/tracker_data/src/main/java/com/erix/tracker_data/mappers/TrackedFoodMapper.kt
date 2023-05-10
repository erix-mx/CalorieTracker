package com.erix.tracker_data.mappers

import com.erix.models.tracker.MealType
import com.erix.models.tracker.TrackableFood
import com.erix.models.tracker.TrackedFood
import com.erix.tracker_data.entity.TrackedFoodEntity
import com.erix.tracker_data.remote.dto.Product
import java.time.LocalDate
import kotlin.math.roundToInt

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}