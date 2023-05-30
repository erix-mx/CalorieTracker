package com.erix.course.philipp.calorytracker.repository

import com.erix.models.tracker.TrackableFood
import com.erix.models.tracker.TrackedFood
import com.erix.tracker_data.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random

class TrackerRepositoryFake: TrackerRepository {
    var shouldReturnError = false

    private val trackedFood = mutableListOf<TrackedFood>()
    var searchResults = listOf<TrackableFood>()

    private val getFoodsForDateFlow =
        MutableSharedFlow<List<TrackedFood>>(replay = 1)



    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return if(shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(searchResults)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackedFood.add(food.copy(id = Random.nextInt()))
        getFoodsForDateFlow.emit(trackedFood)
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackedFood.remove(food)
        getFoodsForDateFlow.emit(trackedFood)
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return getFoodsForDateFlow
    }

}