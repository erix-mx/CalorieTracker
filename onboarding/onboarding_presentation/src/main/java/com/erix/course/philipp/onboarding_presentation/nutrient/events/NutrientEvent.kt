package com.erix.course.philipp.onboarding_presentation.nutrient.events

sealed class NutrientEvent{
    object NavigateToNextScreen: NutrientEvent()
    object NavigateToPreviousScreen: NutrientEvent()
    data class SelectCarbs(val carbs: Float): NutrientEvent()
    data class SelectProteins(val proteins: Float): NutrientEvent()
    data class SelectFats(val fats: Float): NutrientEvent()
    data class SelectTitlePicker(val title: String): NutrientEvent()
}
