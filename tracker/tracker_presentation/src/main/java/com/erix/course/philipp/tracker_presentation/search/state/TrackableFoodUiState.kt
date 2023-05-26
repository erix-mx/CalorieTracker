package com.erix.course.philipp.tracker_presentation.search.state

import com.erix.models.tracker.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: Int = 0
)