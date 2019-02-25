package com.github.rafallopatka.ssc.db

import androidx.room.Room
import com.github.rafallopatka.ssc.App

object DatabaseInitializer {
    lateinit var db: SeriesDatabase
    fun run(app: App) {
        db = Room.databaseBuilder(app, SeriesDatabase::class.java, "series_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}