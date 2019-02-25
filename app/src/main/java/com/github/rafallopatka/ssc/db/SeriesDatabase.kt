package com.github.rafallopatka.ssc.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SeriesEntity::class, SettingsEntity::class], version = 2, exportSchema = false)
abstract class SeriesDatabase : RoomDatabase() {
    abstract fun seriesDao(): SeriesDao
    abstract fun settingsDao(): SettingsDao
}