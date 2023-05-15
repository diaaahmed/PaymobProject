package com.fcm.domain.repo

import com.fcm.domain.entity.*
import com.google.gson.JsonElement
import retrofit2.Response

interface ServicesRepo
{
    suspend fun getToken(api_key:ApiKeyModel): Response<GetTokenResponse>

    suspend fun getOrder(orderModel: OrderModel):Response<OrderResponse>

    suspend fun paymentRequest(paymentRequest: PaymentRequest):Response<GetTokenResponse>

    suspend fun paymentKiosk(kioskRequest: KioskRequest):Response<JsonElement>
}