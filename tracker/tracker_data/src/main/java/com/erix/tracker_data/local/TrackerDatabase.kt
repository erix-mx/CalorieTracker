package com.erix.tracker_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erix.tracker_data.entity.TrackedFoodEntity

@Database(
    entities = [
        TrackedFoodEntity::class,
    ],
    version = 1
)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val trackerDao: TrackerDao
}