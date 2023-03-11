package com.steve_md.mpesa_daraja_sdk.utils


object Constants {

    const val CONNECT_TIMEOUT = 60 * 1000

    const val READ_TIMEOUT = 60 * 1000

    const val WRITE_TIMEOUT = 60 * 1000

    // Sandbox Env
    const val SANDBOX_BASE_URL = "https://sandbox.safaricom.co.ke/"

    // Production Env
    const val Production_BASE_URL = "https://api.safaricom.co.ke/"

    const val BUSINESS_SHORT_CODE = "174379"
    const val PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"
    const val TRANSACTION_TYPE = "CustomerPaybillOnline"
    const val PARTYB = "174379" //same as business shortcode above

    const val CALLBACKURL = "http://mpesa-requestbin.herokuapp.com/1fw79g11"


}