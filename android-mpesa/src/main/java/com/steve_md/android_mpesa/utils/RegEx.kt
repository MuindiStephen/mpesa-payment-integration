package com.steve_md.android_mpesa.utils


import android.util.Base64.NO_WRAP
import android.util.Base64.encodeToString
import java.text.SimpleDateFormat
import java.util.*

object RegEx {
    fun getTimestamp(): String? {
        return SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
    }

    fun sanitizePhoneNumber(phone: String): String? {
        if (phone == "") {
            return ""
        }
        if (phone.length < 11 && phone.startsWith("0")) {
            return phone.replaceFirst("^0".toRegex(), "254")
        }
        return if (phone.length == 13 && phone.startsWith("+")) {
            phone.replaceFirst("^+".toRegex(), "")
        } else phone
    }

    fun getPassword(businessShortCode: String, passkey: String, timestamp: String): String? {
        val str = businessShortCode + passkey + timestamp
        // Encode the password to Base64
        return encodeToString(str.toByteArray(), NO_WRAP)
    }
}