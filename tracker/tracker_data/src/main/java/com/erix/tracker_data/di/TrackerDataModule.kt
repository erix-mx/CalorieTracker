package com.erix.tracker_data.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.erix.tracker_data.local.TrackerDatabase
import com.erix.tracker_data.remote.ApiOpenFoot
import com.erix.tracker_data.remote.ApiOpenFoot.Companion.BASE_URL
import com.erix.tracker_data.repository.TrackerRepository
import com.erix.tracker_data.repository.TrackerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { Log.e("⚡️ Remote:", it) }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiOpenFoot {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create())
            .build()
            .create(ApiOpenFoot::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: ApiOpenFoot,
        db: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            trackerDao = db.trackerDao,
            trackerApi = api
        )
    }

}