package com.erix.course.philipp.tracker_domain.case

import com.erix.models.tracker.TrackedFood
import com.erix.tracker_data.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}