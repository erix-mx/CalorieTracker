package com.erix.tracker_data.repository

import android.util.Log
import com.erix.models.tracker.TrackableFood
import com.erix.models.tracker.TrackedFood
import com.erix.tracker_data.local.TrackerDao
import com.erix.tracker_data.mappers.toTrackableFood
import com.erix.tracker_data.mappers.toTrackedFood
import com.erix.tracker_data.mappers.toTrackedFoodEntity
import com.erix.tracker_data.remote.ApiOpenFoot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl (
    private val trackerApi: ApiOpenFoot,
    private val trackerDao: TrackerDao
) : TrackerRepository {

    override suspend fun searchFood(
        query: String, page: Int, pageSize: Int
    ): Result<List<TrackableFood>> {

        return try {

            val searchDto = trackerApi.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )

            Log.e("TrackerRepositoryImpl", "♻️ ${searchDto.products.size} ♻️ searchFood: $searchDto")

            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                                    it.nutriments.proteins100g * 4f +
                                    it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull { it.toTrackableFood() }

            )
        } catch (e: Exception) {
            Log.e("TrackerRepositoryImpl", "❌searchFood: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackerDao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackerDao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }

}