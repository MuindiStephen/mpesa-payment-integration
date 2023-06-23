package com.steve_md.android_mpesa.dto


import com.google.gson.annotations.SerializedName

/*
 * Successful STK pop up on your phone
 */

data class StkPushSuccessResponse (
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
)