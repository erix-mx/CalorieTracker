package com.erix.course.philipp.tracker_presentation.search.state

import com.erix.models.tracker.TrackableFood

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList(),
    val hasError: Boolean = false,

    val currentFood: TrackableFood? = null,
    val selectedValueAmount: Int = -1,
)
