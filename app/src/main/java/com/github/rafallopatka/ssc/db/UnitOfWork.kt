package com.github.rafallopatka.ssc.db

import io.reactivex.Flowable

class UnitOfWork {

    fun <T> query(action: ((SeriesDatabase) -> T)): Flowable<T> {
        return Flowable.just(DatabaseInitializer.db)
            .map { action.invoke(it) }
    }

    fun <T: Any> transaction(action: ((SeriesDatabase) -> T)): Flowable<TransactionResult<T>> {
        return Flowable.just(DatabaseInitializer.db)
            .map { db ->
                lateinit var result: TransactionResult<T>
                try {
                    db.runInTransaction {
                        val res = action(db)
                        result = TransactionResult.Success(res)
                    }
                } catch (e: Throwable) {
                    result = TransactionResult.Failure(e)
                }
                return@map result
            }.onErrorReturn { TransactionResult.Failure(it) }
    }
}