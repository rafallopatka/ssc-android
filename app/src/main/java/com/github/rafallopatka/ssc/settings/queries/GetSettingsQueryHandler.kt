package com.github.rafallopatka.ssc.settings.queries

import com.github.rafallopatka.ssc.core.QueryHandler
import com.github.rafallopatka.ssc.db.SettingsEntity
import com.github.rafallopatka.ssc.db.UnitOfWork
import com.github.rafallopatka.ssc.settings.logic.SettingsDto
import io.reactivex.Flowable

class GetSettingsQueryHandler(private val unitOfWork: UnitOfWork):
    QueryHandler<GetSettingsQuery, SettingsDto> {
    override fun handle(query: GetSettingsQuery): Flowable<SettingsDto> {
        return unitOfWork.query { db ->
            val dao = db.settingsDao()

            val settings = dao.get()
            return@query SettingsDto(
                address = settings?.address ?: ""
            )
        }
    }

}