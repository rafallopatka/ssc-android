package com.github.rafallopatka.ssc.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Series")
class SeriesEntity {
    @NonNull
    @PrimaryKey
    var seriesId: String? = null

    @NonNull
    var name: String? = null

    @NonNull
    var sequence: String? = null
}