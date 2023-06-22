package com.steve_md.mpesa_daraja_sdk.data.api

import com.steve_md.mpesa_daraja_sdk.dto.AuthorizationResponse
import com.steve_md.mpesa_daraja_sdk.dto.StkPushRequest
import com.steve_md.mpesa_daraja_sdk.dto.StkPushSuccessResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DarajaApiService {

      @POST("mpesa/stkpush/v1/processrequest")
       fun sendPush(
          @Body
          stkPushRequest: StkPushRequest
      ) : Call<StkPushSuccessResponse>

      @GET("oauth/v1/generate?grant_type=client_credentials")
       fun getAccessToken() : Call<AuthorizationResponse>
}