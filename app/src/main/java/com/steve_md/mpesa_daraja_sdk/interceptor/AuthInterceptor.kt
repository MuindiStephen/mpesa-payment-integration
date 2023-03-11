package com.steve_md.mpesa_daraja_sdk.interceptor

import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class AuthInterceptor :  Interceptor {
    private var mAuthToken: String? = null

    @SuppressLint("NotConstructor")
    fun AuthInterceptor(authToken: String) {
        mAuthToken = authToken
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $mAuthToken")
            .build()
        return chain.proceed(request)
    }
}