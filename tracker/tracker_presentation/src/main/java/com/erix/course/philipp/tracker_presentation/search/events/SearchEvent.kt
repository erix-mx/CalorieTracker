package com.erix.course.philipp.tracker_presentation.search.events

import com.erix.models.tracker.MealType
import com.erix.models.tracker.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    object ClearQuery : SearchEvent()
    data class OnQueryChange(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(
        val food: TrackableFood,
        val amount: Int
    ) : SearchEvent()
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ): SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()

    data class OnCurrentFood(val food: TrackableFood): SearchEvent()
    data class OnSelectedValueAmount(val amount: Int): SearchEvent()

    data class OnAddFoodClick(
        val date: LocalDate,
        val mealTypeName: String,
    ): SearchEvent()


}