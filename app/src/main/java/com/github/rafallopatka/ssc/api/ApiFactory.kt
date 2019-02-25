package com.github.rafallopatka.ssc.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiFactory {
    private lateinit var retrofit: Retrofit

    fun init() {
        val baseUrlPlaceholder = "http://{base-url-placeholder}"

        val baseUriInterceptor =
            BaseUriInterceptor(ApiDiscoveryObject, baseUrlPlaceholder)

        val client = OkHttpClient.Builder()
            .addInterceptor(baseUriInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrlPlaceholder)
            .build()
    }

    fun create(): Api {
        return retrofit.create(Api::class.java)
    }
}