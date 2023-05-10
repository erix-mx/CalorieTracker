package com.erix.course.philipp.calorytracker.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.erix.course.philipp.core_main.domain.preferences.DefaultPreferences
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("calory_tracker", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providerDefaultPreferences(sharedPreferences: SharedPreferences): Preferences =
        DefaultPreferences(sharedPreferences)
}