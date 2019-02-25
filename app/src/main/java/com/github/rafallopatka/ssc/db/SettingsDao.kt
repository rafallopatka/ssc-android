package com.github.rafallopatka.ssc.db

import androidx.room.*

@Dao
interface SettingsDao {
    @Insert
    fun insert(settingsEntity: SettingsEntity)

    @Query("SELECT * FROM Settings LIMIT 1")
    fun get(): SettingsEntity?

    @Update
    fun update(series: SettingsEntity)
}