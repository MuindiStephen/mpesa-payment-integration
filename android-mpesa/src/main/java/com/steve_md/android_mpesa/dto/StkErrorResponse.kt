package com.steve_md.android_mpesa.dto

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
