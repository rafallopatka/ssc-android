package com.github.rafallopatka.ssc.db

import androidx.room.*


@Dao
interface SeriesDao {
    @Insert
    fun insertSingle(series: SeriesEntity)

    @Insert
    fun insertBulk(seriesCollection: List<SeriesEntity>)

    @Query("SELECT * FROM Series WHERE seriesId = :seriesId")
    fun getById(seriesId: String): SeriesEntity

    @Query("SELECT * FROM Series ORDER BY name ASC LIMIT :take OFFSET :skip")
    fun fetch(skip: Int, take: Int): List<SeriesEntity>

    @Update
    fun updateSeries(series: SeriesEntity)

    @Delete
    fun deleteSeries(series: SeriesEntity)
}

