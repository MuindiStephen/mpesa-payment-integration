package com.steve_md.android_mpesa.dto

import com.google.gson.annotations.SerializedName

/**
 * A request sent to the API to start the STK push payment on your phone
 * **/

data class StkPushRequest(
    @field:SerializedName("BusinessShortCode")
    private val businessShortCode: String,

    @field:SerializedName(
        "Password"
    ) private val password: String,

    @field:SerializedName("Timestamp")
    private val timestamp: String,

    @field:SerializedName(
        "TransactionType"
    ) private val transactionType: String,


    @field:SerializedName("Amount")
    private val amount: String,


    @field:SerializedName("PartyA")
    private val partyA: String,


    @field:SerializedName(
        "PartyB"
    ) private val partyB: String,


    @field:SerializedName("PhoneNumber")
    private val phoneNumber: String,


    @field:SerializedName(
        "CallBackURL"
    )
    private val callBackURL: String,


    @field:SerializedName("AccountReference")
    private val accountReference: String,


    @field:SerializedName(
        "TransactionDesc"
    ) private val transactionDesc: String
)