package com.example.data_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class GeneralHeaderInterceptor: Interceptor  {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithHeader = originalRequest.newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()

        return chain.proceed(requestWithHeader)
    }
}
