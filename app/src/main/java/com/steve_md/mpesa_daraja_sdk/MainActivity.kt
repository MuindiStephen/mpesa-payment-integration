package com.steve_md.mpesa_daraja_sdk

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.steve_md.mpesa_daraja_sdk.data.api.DarajaApiClient
import com.steve_md.mpesa_daraja_sdk.dto.AuthorizationResponse
import com.steve_md.mpesa_daraja_sdk.dto.StkPushRequest
import com.steve_md.mpesa_daraja_sdk.dto.StkPushSuccessResponse
import com.steve_md.mpesa_daraja_sdk.utils.Constants
import com.steve_md.mpesa_daraja_sdk.utils.Constants.BUSINESS_SHORT_CODE
import com.steve_md.mpesa_daraja_sdk.utils.Constants.CALLBACKURL
import com.steve_md.mpesa_daraja_sdk.utils.Constants.PARTYB
import com.steve_md.mpesa_daraja_sdk.utils.Constants.PASSKEY
import com.steve_md.mpesa_daraja_sdk.utils.Constants.SANDBOX_BASE_URL
import com.steve_md.mpesa_daraja_sdk.utils.RegEx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private var mApiClient: DarajaApiClient? = null

    var mAmount: EditText? = null
    var mPhone: EditText? = null
    var mPay: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        title = "LIPA NA MPESA"

        mAmount= findViewById(R.id.enterAmountEditText)
        mPhone = findViewById(R.id.enterPhoneEditText)
        mPay = findViewById(R.id.buttonPay)

        val consumerKey = BuildConfig.DARAJA_CONSUMER_KEY
        val consumerSecret = BuildConfig.DARAJA_CONSUMER_SECRET


        mApiClient = DarajaApiClient(
            consumerKey,
            consumerSecret,
            SANDBOX_BASE_URL
        )
        mApiClient!!.setIsDebug(true)
        mPay!!.setOnClickListener(this)

        getAccessToken()
    }

    private fun getAccessToken() {
        mApiClient!!.setGetAccessToken(true)
        mApiClient!!.mpesaService()!!.getAccessToken().enqueue(object :
            Callback<AuthorizationResponse?> {
            override fun onResponse(call: Call<AuthorizationResponse?>, response: Response<AuthorizationResponse?>) {
                if (response.isSuccessful) {
                    mApiClient!!.setAuthToken(response.body()?.accessToken)
                }
            }

            override fun onFailure(call: Call<AuthorizationResponse?>, t: Throwable) {
                Log.e("MainActivity",t.printStackTrace().toString())
            }
        })
    }

    override fun onClick(v: View?) {
        if (v === mPay) {
            val phoneNumber = mPhone!!.text.toString()
            val amount = mAmount!!.text.toString()


            performSTKPush(phoneNumber, amount)
        }
    }

    private fun performSTKPush(phoneNumber: String, amount: String) {
        val timestamp = RegEx.getTimestamp()

        val stkPush = StkPushRequest(
            businessShortCode = BUSINESS_SHORT_CODE,
            password = RegEx.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp!!)!!,
            timestamp = timestamp,
            transactionType = Constants.TransactionType.CustomerPayBillOnline,
            amount = amount,
            partyA = RegEx.sanitizePhoneNumber(phoneNumber)!! ,
            partyB = PARTYB,
            phoneNumber = RegEx.sanitizePhoneNumber(phoneNumber)!!,
            callBackURL = CALLBACKURL,
            accountReference = "LIPA NA MPESA",
            transactionDesc = "LIPA NA MPESA C2B"
        )

        mApiClient!!.setGetAccessToken(false)

        mApiClient!!.mpesaService()!!.sendPush(stkPush).enqueue(object : Callback<StkPushSuccessResponse> {
            override fun onResponse(call: Call<StkPushSuccessResponse>, response: Response<StkPushSuccessResponse>) {

                try {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@MainActivity,
                            "Response : ${response.body().toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG,"post submitted to API. %s")
                    } else {
                        Log.e(TAG,"Response %s")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<StkPushSuccessResponse>, t: Throwable) {
                Log.e(TAG,t.printStackTrace().toString(), httpException)

            }
        })
    }
    companion object {
        val httpException : HttpException? = null
        const val TAG = "MainActivity"
    }
}