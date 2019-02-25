package com.github.rafallopatka.ssc.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Settings")
class SettingsEntity {
    @NonNull
    @PrimaryKey
    var settingsId: String? = null

    @NonNull
    var address: String? = null
}