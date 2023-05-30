package com.erix.course.philipp.tracker_presentation.tracker_overview.event


import com.erix.course.philipp.tracker_presentation.components.Meal
import com.erix.models.tracker.TrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClick: TrackerOverviewEvent()
    object OnPreviousDayClick: TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvent()
    data class OnAddFoodClick(val meal: Meal): TrackerOverviewEvent()

    object OnSettingsClick: TrackerOverviewEvent()


}