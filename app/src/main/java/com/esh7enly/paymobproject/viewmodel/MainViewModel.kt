package com.esh7enly.paymobproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esh7enly.paymobproject.*
import com.fcm.domain.entity.*
import com.fcm.domain.usecase.GetServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

// Don`t forget replace with your payment key
private const val paymentKey = "ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmpiR0Z6Y3lJNklrMWxjbU5vWVc1MElpd2ljSEp2Wm1sc1pWOXdheUk2TkRrMU1UY3lMQ0p1WVcxbElqb2lNVFk0TkRBM01ETTJOaTR5T1RRd01qY2lmUS5tM1NEdlFKN1hfQzJ2NEJaOVl3S3NFUkYyT0dmRkN4c0QwVDRfX2pXZEpmaHZMSUZDZFU0S3ZzUGlldUxaX2FyMFFfOGtKMnF3RzFPci1LeDVPQmc1Zw=="

private const val integrationIdCard = 2765637
private const val integrationIdKiosk = 3707140

@HiltViewModel
class MainViewModel @Inject constructor(private val getServices: GetServices)
    : ViewModel() {

    private val _kioskCode: MutableLiveData<String> = MutableLiveData<String>()
    val kioskCode: LiveData<String> = _kioskCode

    private val _move: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val move: LiveData<Boolean> = _move

    private fun getKioskPayment(finalToken:String) {
        viewModelScope.launch {
           val paymentKioskResponse =  getServices.paymentKiosk(
               KioskRequest(
                   finalToken,
                   Source("AGGREGATOR", "AGGREGATOR")
               )
           )

            if(paymentKioskResponse.isSuccessful)
            {
                val jsonObject = paymentKioskResponse.body()?.asJsonObject
                val id = jsonObject?.get("id")?.asInt
                Log.d(TAG, "diaa getKiosk deon: $id")
                _kioskCode.value = id.toString()
                //  Log.d(TAG, "diaa getKiosk done ${paymentKioskResponse.body()?.id}")
            }
            else
            {
                Log.d(
                    TAG, "diaa paymentKioskResponse error body ${paymentKioskResponse.code()}" +
                            " ${paymentKioskResponse.message()}"
                )
            }
        }
    }

    fun getToken() {
        viewModelScope.launch {
            try{
               // val tokenResponse = apiInterface.getToken(ApiKeyModel(paymentKey))
                val tokenResponse = getServices.getToken(ApiKeyModel(paymentKey))

                if(tokenResponse.isSuccessful)
                {
                    Log.d(TAG, "diaa getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                    getOrderID(token.toString())

                }
                else
                {
                    Log.d(
                        TAG,
                        "diaa getToken error body ${tokenResponse.code()} ${tokenResponse.message()}"
                    )

                }

            }
            catch(e:Exception)
            {

                Log.d(TAG, "diaa getToken error: ${e.message}")
            }
        }
    }

    private suspend fun getOrderID(token:String) {
        val orderResponse =  getServices.getOrder(
            OrderModel(token, "false", "20000", "EGP")
        )

        if(orderResponse.isSuccessful)
        {
            Log.d(TAG, "diaa getOrder done ${orderResponse.body()?.id}")
            val orderId = orderResponse.body()?.id

            getPaymentRequest(orderId.toString(),token)

        }
        else
        {
            Log.d(
                TAG, "diaa getOrder error body ${orderResponse.code()}" +
                        " ${orderResponse.message()}"
            )

        }
    }

    private suspend fun getPaymentRequest(orderId:String,token:String) {
        val requestModel = PaymentRequest(
            "20000", token,
            BillingData(
                "NA", "NA", "NA", "NA",
                "diaaahmedh200@gmail.com", "Diaa", "NA",
                "Ahmed", "01028237267", "NA", "NA",
                "NA", "NA"
            ), "EGP", 3600, integrationIdCard,
            "true",
            orderId
        )

        val paymentResponse = getServices.paymentRequest(requestModel)

        if(paymentResponse.isSuccessful)
        {
            Log.d(TAG, "diaa paymentRequest done ${paymentResponse.body()?.token}")

            val finalToken = paymentResponse.body()?.token
            Constant.finalToken = finalToken.toString()
            _move.value = true
            //  getKioskPayment(finalToken.toString())

        }
        else
        {
            Log.d(
                TAG, "diaa paymentRequest error body ${paymentResponse.code()}" +
                        " ${paymentResponse.message()}"
            )
        }
    }

    fun getTokenKiosk() {
        viewModelScope.launch {
            try{
                val tokenResponse = getServices.getToken(ApiKeyModel(paymentKey))

                if(tokenResponse.isSuccessful)
                {
                    Log.d(TAG, "diaa getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                    val orderResponse =  getServices.getOrder(
                        OrderModel(token.toString(), "false", "20000", "EGP")
                    )

                    if(orderResponse.isSuccessful)
                    {
                        Log.d(TAG, "diaa getOrder done ${orderResponse.body()?.id}")
                        val orderId = orderResponse.body()?.id

                        val requestModel = PaymentRequest(
                            "20000", token.toString(),
                            BillingData(
                                "NA", "NA", "NA", "NA",
                                "diaaahmedh200@gmail.com", "Diaa", "NA",
                                "Ahmed", "01028237267", "NA", "NA",
                                "NA", "NA"
                            ), "EGP", 3600, integrationIdKiosk,
                            "true",
                            orderId.toString()
                        )

                        val paymentResponse = getServices.paymentRequest(requestModel)

                        if(paymentResponse.isSuccessful)
                        {
                            Log.d(TAG, "diaa paymentRequest done ${paymentResponse.body()?.token}")

                            val finalToken = paymentResponse.body()?.token
                            //Constant.finalToken = finalToken.toString()
                           // _move.value = true
                            getKioskPayment(finalToken.toString())

                        }
                        else
                        {
                            Log.d(
                                TAG, "diaa paymentRequest error body ${paymentResponse.code()}" +
                                        " ${paymentResponse.message()}"
                            )
                        }
                    }
                    else
                    {
                        Log.d(
                            TAG, "diaa getOrder error body ${orderResponse.code()}" +
                                    " ${orderResponse.message()}"
                        )

                    }
                }
                else
                {
                    Log.d(
                        TAG,
                        "diaa getToken error body ${tokenResponse.code()} ${tokenResponse.message()}"
                    )

                }

            }
            catch(e:Exception)
            {

                Log.d(TAG, "diaa getToken error: ${e.message}")
            }
        }
    }

}