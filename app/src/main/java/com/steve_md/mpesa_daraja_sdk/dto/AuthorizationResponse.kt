package com.steve_md.mpesa_daraja_sdk.dto


import com.google.gson.annotations.SerializedName

/*
* @param [accessToken] - The exact toke
* @param [expiresIn] - When the token expires
*  */

data class AuthorizationResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: String
)