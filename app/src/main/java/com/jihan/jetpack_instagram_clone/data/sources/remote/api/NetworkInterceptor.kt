package com.jihan.jetpack_instagram_clone.data.sources.remote.api

import com.jihan.jetpack_instagram_clone.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = tokenManager.getToken()

        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())

    }
}