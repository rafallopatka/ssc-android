package com.github.rafallopatka.ssc.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("runSequence/{sequence}")
    fun runSequence(@Path("sequence") sequence: String): Flowable<Unit>
}