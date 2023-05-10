package com.erix.tracker_data.mappers

import com.erix.models.tracker.TrackableFood
import com.erix.tracker_data.remote.dto.Product
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val carbsPer100g = nutriments.carbohydrates.roundToInt()
    val proteinPer100g = nutriments.proteins.roundToInt()
    val fatPer100g = nutriments.fat.roundToInt()
    val caloriesPer100g = nutriments.energy.roundToInt()
    return TrackableFood(
        name = productName,
        carbsPer100g = carbsPer100g,
        proteinPer100g = proteinPer100g,
        fatPer100g = fatPer100g,
        caloriesPer100g = caloriesPer100g,
        imageUrl = imageThumbnailUrl
    )
}