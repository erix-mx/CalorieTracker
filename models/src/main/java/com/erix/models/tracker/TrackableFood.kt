package com.erix.models.tracker

data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val proteinPer100g: Int,
    val fatPer100g: Int
) {
    fun toItem(): TrackedFood {
        return TrackedFood(
            name = name,
            imageUrl = imageUrl,
            calories = caloriesPer100g,
            carbs = carbsPer100g,
            protein = proteinPer100g,
            fat = fatPer100g,
            amount = 100,
        )
    }
}
