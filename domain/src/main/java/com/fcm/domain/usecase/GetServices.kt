package com.fcm.domain.usecase

import com.fcm.domain.entity.*
import com.fcm.domain.repo.ServicesRepo


class GetServices(private val servicesRepo: ServicesRepo)
{
    suspend fun getToken(api_key: ApiKeyModel) = servicesRepo.getToken(api_key)

    suspend fun getOrder(orderModel: OrderModel) = servicesRepo.getOrder(orderModel)

    suspend fun paymentRequest(paymentRequest: PaymentRequest) = servicesRepo.paymentRequest(paymentRequest)

    suspend fun paymentKiosk(kioskRequest: KioskRequest) = servicesRepo.paymentKiosk(kioskRequest)
}