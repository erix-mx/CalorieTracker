package com.erix.course.philipp.onboarding_presentation.nutrient.state

data class NutrientState(
    val selectedCarbs: Float = 0f,
    val selectedProteins: Float = 0f,
    val selectedFats: Float = 0f,
    val currentTitlePicker: String = "",
)
