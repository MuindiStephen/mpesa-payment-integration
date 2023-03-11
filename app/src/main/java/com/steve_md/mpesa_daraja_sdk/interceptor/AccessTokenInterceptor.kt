package com.steve_md.mpesa_daraja_sdk.interceptor

import okhttp3.Interceptor
import okhttp3.Response



/**
 *
 * This class encode the Consumer Key and Secret to
 * create the auth token and pass it as a header for our JSON request to the API
 *
 */
class AccessTokenInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
                TODO()
        }

}