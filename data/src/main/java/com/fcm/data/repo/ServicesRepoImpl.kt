package com.fcm.data.repo

import com.fcm.data.remote.ApiInterface
import com.fcm.domain.entity.*
import com.fcm.domain.repo.ServicesRepo
import com.google.gson.JsonElement
import retrofit2.Response

class ServicesRepoImpl(private val apiService: ApiInterface):ServicesRepo
{

    override suspend fun getToken(api_key: ApiKeyModel): Response<GetTokenResponse> = apiService.getToken(api_key)

    override suspend fun getOrder(orderModel: OrderModel): Response<OrderResponse> = apiService.getOrder(orderModel)

    override suspend fun paymentRequest(paymentRequest: PaymentRequest): Response<GetTokenResponse> = apiService.paymentRequest(paymentRequest)

    override suspend fun paymentKiosk(kioskRequest: KioskRequest): Response<JsonElement> = apiService.paymentKiosk(kioskRequest)
}