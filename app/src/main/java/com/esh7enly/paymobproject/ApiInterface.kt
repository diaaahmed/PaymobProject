package com.esh7enly.paymobproject

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface
{
    @POST("auth/tokens")
    suspend fun getToken(@Body api_key:ApiKeyModel):Response<GetTokenResponse>

    @POST("ecommerce/orders")
    suspend fun getOrder(@Body orderModel: OrderModel):Response<OrderResponse>

    @POST("acceptance/payment_keys")
    suspend fun paymentRequest(@Body paymentRequest: PaymentRequest):Response<GetTokenResponse>

    @POST("acceptance/payments/pay")
    suspend fun paymentKiosk(@Body kioskRequest: KioskRequest):Response<JsonElement>

}