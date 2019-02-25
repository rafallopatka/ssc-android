package com.github.rafallopatka.ssc.settings.commands

import com.github.rafallopatka.ssc.api.ApiDiscoveryObject
import com.github.rafallopatka.ssc.core.CommandHandler
import com.github.rafallopatka.ssc.db.SettingsEntity
import com.github.rafallopatka.ssc.db.UnitOfWork
import io.reactivex.Flowable
import java.util.*

class SaveSettingsCommandHandler(private val unitOfWork: UnitOfWork):
    CommandHandler<SaveSettingsCommand> {
    override fun process(command: SaveSettingsCommand): Flowable<Unit> {
        return unitOfWork.transaction { db ->
            val dao = db.settingsDao()
            val item = dao.get()
            if (item == null){
                val settings = SettingsEntity().apply {
                    settingsId = UUID.randomUUID().toString()
                    address = command.address
                }
                dao.insert(settings)
            } else {
                item.address = command.address
                dao.update(item)
            }
        }.map { Unit }.doOnNext {
            ApiDiscoveryObject.address = command.address
        }
    }
}