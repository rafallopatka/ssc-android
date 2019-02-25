package com.github.rafallopatka.ssc.api

import android.annotation.SuppressLint
import com.github.rafallopatka.ssc.core.ServiceResolver
import com.github.rafallopatka.ssc.db.SettingsEntity
import com.github.rafallopatka.ssc.db.UnitOfWork
import io.reactivex.schedulers.Schedulers

object ApiDiscoveryObject : ApiDiscovery {
    override var address: String = ""

    @SuppressLint("CheckResult")
    fun load() {
        val unitOfWork = ServiceResolver.get<UnitOfWork>()
        unitOfWork.query { db ->
            val settings = db.settingsDao()
            settings.get()?:SettingsEntity()
        }.subscribeOn(Schedulers.io())
            .subscribe {
                address = it?.address ?: ""
            }
    }
}