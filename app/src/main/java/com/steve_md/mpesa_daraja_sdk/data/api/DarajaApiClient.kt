package com.steve_md.mpesa_daraja_sdk.data.api


import com.steve_md.mpesa_daraja_sdk.interceptor.AccessTokenInterceptor
import com.steve_md.mpesa_daraja_sdk.interceptor.AuthInterceptor
import com.steve_md.mpesa_daraja_sdk.utils.Constants.CONNECT_TIMEOUT
import com.steve_md.mpesa_daraja_sdk.utils.Constants.READ_TIMEOUT
import com.steve_md.mpesa_daraja_sdk.utils.Constants.SANDBOX_BASE_URL
import com.steve_md.mpesa_daraja_sdk.utils.Constants.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Utilize Retrofit & Gson to handle our API calls
 */

class DarajaApiClient(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val environment: String
) {
    private var retrofit: Retrofit? = null
    private var isDebug = false
    private var isGetAccessToken = false
    private var mAuthToken: String? = null
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    fun setIsDebug(isDebug: Boolean): DarajaApiClient? {
        this.isDebug = isDebug
        return this
    }

    fun setAuthToken(authToken: String?): DarajaApiClient? {
        mAuthToken = authToken
        return this
    }

    fun setGetAccessToken(getAccessToken: Boolean): DarajaApiClient? {
        isGetAccessToken = getAccessToken
        return this
    }

    private fun okHttpClient(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
        return okHttpClient
    }

    private fun getRestAdapter(): Retrofit? {
        val builder = Retrofit.Builder()
        builder.baseUrl(environment)
        builder.addConverterFactory(GsonConverterFactory.create())
        if (isDebug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        val okhttpBuilder = okHttpClient()
        if (isGetAccessToken) {
            okhttpBuilder.addInterceptor(AccessTokenInterceptor(consumerKey, consumerSecret))
        }
        if (mAuthToken != null && !mAuthToken!!.isEmpty()) {
            okhttpBuilder.addInterceptor(AuthInterceptor(mAuthToken!!))
        }
        builder.client(okhttpBuilder.build())
        retrofit = builder.build()
        return retrofit
    }

    fun mpesaService(): DarajaApiService? {
        return getRestAdapter()!!.create(DarajaApiService::class.java)
    }
}