package com.erix.course.philipp.tracker_domain.di


import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.tracker_domain.usescase.CalculateMealNutrients
import com.erix.course.philipp.tracker_domain.usescase.DeleteTrackedFood
import com.erix.course.philipp.tracker_domain.usescase.GetFoodsForDate
import com.erix.course.philipp.tracker_domain.usescase.SearchFood
import com.erix.course.philipp.tracker_domain.usescase.TrackFood
import com.erix.course.philipp.tracker_domain.usescase.TrackerUseCases
import com.erix.tracker_data.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class TrackerDomainModule {


    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases = TrackerUseCases(
               trackFood = TrackFood(repository),
                searchFood = SearchFood(repository),
                getFoodsForDate = GetFoodsForDate(repository),
                deleteTrackedFood = DeleteTrackedFood(repository),
                calculateMealNutrients = CalculateMealNutrients(preferences)
    )

}