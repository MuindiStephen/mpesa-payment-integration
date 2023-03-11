package com.steve_md.mpesa_daraja_sdk.dto

import com.google.gson.annotations.SerializedName

/*
 * In case of any Stk push error (:    ?
 */
data class StkErrorResponse(
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("errorCode")
    val errorCode:String,
    @SerializedName("errorMessage")
    val errorMessage:String

)
