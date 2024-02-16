package com.example.data_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UserTokenInterceptor(
    private val userToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeader = originalRequest.newBuilder()

        requestWithHeader.header("Authorization", userToken)

        return chain.proceed(requestWithHeader.build())
    }
}