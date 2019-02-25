package com.github.rafallopatka.ssc.api

import okhttp3.Interceptor
import okhttp3.Response

class BaseUriInterceptor(private val apiDiscovery: ApiDiscovery,
                         private val placeHolder: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        val baseUri = request.url().toString()
        if (baseUri.contains(placeHolder)){
            val url = baseUri.replace(placeHolder, apiDiscovery.address)
            builder.url(url)
        }

        return chain.proceed(builder.build())
    }
}