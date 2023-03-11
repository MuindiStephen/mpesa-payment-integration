package com.steve_md.mpesa_daraja_sdk.dto


import com.google.gson.annotations.SerializedName

data class StkPushResponse(
    @SerializedName("CheckoutRequestID")
    val checkoutRequestID: String,
    @SerializedName("CustomerMessage")
    val customerMessage: String,
    @SerializedName("MerchantRequestID")
    val merchantRequestID: String,
    @SerializedName("ResponseCode")
    val responseCode: String,
    @SerializedName("ResponseDescription")
    val responseDescription: String,

    /*
     * In case of any error
     */
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("errorCode")
    val errorCode:String,
    @SerializedName("errorMessage")
    val errorMessage:String
)