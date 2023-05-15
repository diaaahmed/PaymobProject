package com.fcm.domain.entity

data class GetTokenResponse(
    val profile: Profile,
    val token: String
)