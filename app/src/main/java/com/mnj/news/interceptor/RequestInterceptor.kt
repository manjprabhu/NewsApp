package com.mnj.news.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "auth-value")   // adding new header key-value pair
            .addHeader("User-agent", "useragent")
            .build()

        return chain.proceed(newRequest)
    }
}