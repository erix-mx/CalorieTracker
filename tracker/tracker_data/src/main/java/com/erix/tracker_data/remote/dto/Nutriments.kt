package com.erix.tracker_data.remote.dto

import com.squareup.moshi.Json

data class Nutriments(
    @field:Json(name = "carbohydrates_100g" ) val carbohydrates: Double,
    @field:Json(name = "energy-kcal_100g"   ) val fat: Double,
    @field:Json(name = "fat_100g"           ) val proteins: Double,
    @field:Json(name = "energy-kcal_100g"   ) val energy: Double,
)