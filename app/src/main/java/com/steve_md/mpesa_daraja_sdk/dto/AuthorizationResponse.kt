package com.steve_md.mpesa_daraja_sdk.dto


import com.google.gson.annotations.SerializedName

data class AuthorizationResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: String
)