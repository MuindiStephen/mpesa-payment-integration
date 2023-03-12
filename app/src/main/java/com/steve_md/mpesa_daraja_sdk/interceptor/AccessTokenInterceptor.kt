package com.steve_md.mpesa_daraja_sdk.interceptor


import android.util.Base64
import com.steve_md.mpesa_daraja_sdk.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 *
 * This class encode the Consumer Key and Secret to
 * create the auth token and pass it as a header for our JSON request to the API
 *
 */

class AccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val keys = BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET

        // Utilities for encoding and decoding the Base64 representation of binary data
        val request: Request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP)
            )
            .build()
        return chain.proceed(request)
    }
}