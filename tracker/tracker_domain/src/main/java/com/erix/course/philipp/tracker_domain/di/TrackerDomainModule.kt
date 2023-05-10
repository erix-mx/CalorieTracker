package com.erix.course.philipp.tracker_domain.di

import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.tracker_domain.case.CalculateMealNutrients
import com.erix.course.philipp.tracker_domain.case.DeleteTrackedFood
import com.erix.course.philipp.tracker_domain.case.GetFoodsForDate
import com.erix.course.philipp.tracker_domain.case.SearchFood
import com.erix.course.philipp.tracker_domain.case.TrackFood
import com.erix.course.philipp.tracker_domain.case.TrackerUseCases
import com.erix.tracker_data.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped



@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }

}