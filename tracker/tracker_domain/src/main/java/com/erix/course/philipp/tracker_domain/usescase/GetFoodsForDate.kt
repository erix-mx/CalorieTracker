package com.erix.course.philipp.tracker_domain.usescase

import com.erix.models.tracker.TrackedFood
import com.erix.tracker_data.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}