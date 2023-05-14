package com.esh7enly.paymobproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

// Don`t forget replace with your payment key
private val paymentKey = "ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmpiR0Z6Y3lJNklrMWxjbU5vWVc1MElpd2ljSEp2Wm1sc1pWOXdheUk2TkRrMU1UY3lMQ0p1WVcxbElqb2lNVFk0TkRBM01ETTJOaTR5T1RRd01qY2lmUS5tM1NEdlFKN1hfQzJ2NEJaOVl3S3NFUkYyT0dmRkN4c0QwVDRfX2pXZEpmaHZMSUZDZFU0S3ZzUGlldUxaX2FyMFFfOGtKMnF3RzFPci1LeDVPQmc1Zw=="

private val integrationIdCard = 2765637
private val integrationIdKiosk = 3707140

@HiltViewModel
class MainViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    private val _kioskCode:MutableLiveData<String> = MutableLiveData<String>()
    val kioskCode:LiveData<String> = _kioskCode

    private val _move:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val move:LiveData<Boolean> = _move

    fun getKioskPayment(finalToken:String)
    {
        viewModelScope.launch {
           val paymentKioskResponse =  apiInterface.paymentKiosk(KioskRequest(finalToken,
                Source("AGGREGATOR","AGGREGATOR")))

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
                Log.d(TAG, "diaa paymentKioskResponse error body ${paymentKioskResponse.code()}" +
                        " ${paymentKioskResponse.message()}")
            }
        }
    }

    fun getToken() {
        viewModelScope.launch {
            try{
                val tokenResponse = apiInterface.getToken(ApiKeyModel(paymentKey))

                if(tokenResponse.isSuccessful)
                {
                    Log.d(TAG, "diaa getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                   val orderResponse =  apiInterface.getOrder(
                        OrderModel(token.toString(),"false","20000","EGP"))

                    if(orderResponse.isSuccessful)
                    {
                        Log.d(TAG, "diaa getOrder done ${orderResponse.body()?.id}")
                        val orderId = orderResponse.body()?.id

                        val requestModel = PaymentRequest("20000",token.toString(),
                        BillingData("NA","NA","NA","NA",
                        "diaaahmedh200@gmail.com","Diaa","NA",
                        "Ahmed","01028237267","NA","NA",
                        "NA","NA"),"EGP",3600, integrationIdCard,
                            "true",
                        orderId.toString())

                        val paymentResponse = apiInterface.paymentRequest(requestModel)

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
                            Log.d(TAG, "diaa paymentRequest error body ${paymentResponse.code()}" +
                                    " ${paymentResponse.message()}")
                        }
                    }
                    else
                    {
                        Log.d(TAG, "diaa getOrder error body ${orderResponse.code()}" +
                                " ${orderResponse.message()}")

                    }
                }
                else
                {
                    Log.d(TAG, "diaa getToken error body ${tokenResponse.code()} ${tokenResponse.message()}")

                }

            }
            catch(e:Exception)
            {

                Log.d(TAG, "diaa getToken error: ${e.message}")
            }
        }
    }

    fun getTokenKiosk() {
        viewModelScope.launch {
            try{
                val tokenResponse = apiInterface.getToken(ApiKeyModel(paymentKey))

                if(tokenResponse.isSuccessful)
                {
                    Log.d(TAG, "diaa getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                    val orderResponse =  apiInterface.getOrder(
                        OrderModel(token.toString(),"false","20000","EGP"))

                    if(orderResponse.isSuccessful)
                    {
                        Log.d(TAG, "diaa getOrder done ${orderResponse.body()?.id}")
                        val orderId = orderResponse.body()?.id

                        val requestModel = PaymentRequest("20000",token.toString(),
                            BillingData("NA","NA","NA","NA",
                                "diaaahmedh200@gmail.com","Diaa","NA",
                                "Ahmed","01028237267","NA","NA",
                                "NA","NA"),"EGP",3600, integrationIdKiosk,
                            "true",
                            orderId.toString())

                        val paymentResponse = apiInterface.paymentRequest(requestModel)

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
                            Log.d(TAG, "diaa paymentRequest error body ${paymentResponse.code()}" +
                                    " ${paymentResponse.message()}")
                        }
                    }
                    else
                    {
                        Log.d(TAG, "diaa getOrder error body ${orderResponse.code()}" +
                                " ${orderResponse.message()}")

                    }
                }
                else
                {
                    Log.d(TAG, "diaa getToken error body ${tokenResponse.code()} ${tokenResponse.message()}")

                }

            }
            catch(e:Exception)
            {

                Log.d(TAG, "diaa getToken error: ${e.message}")
            }
        }
    }

}