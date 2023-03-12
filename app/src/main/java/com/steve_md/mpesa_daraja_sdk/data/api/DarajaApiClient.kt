package com.steve_md.mpesa_daraja_sdk.data.api

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
object DarajaApiClient {

      // Http Logging Interceptor
      private val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

      // OkHttpClient
      private val okHttpClient  = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)



      private val retrofit = Retrofit.Builder()
            .baseUrl(SANDBOX_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()

     val darajaApi  by lazy { retrofit.create(DarajaApiService::class.java) }

}