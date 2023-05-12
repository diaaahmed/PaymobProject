package com.esh7enly.paymobproject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KioskRequest(
    @Expose
    @SerializedName("payment_token")
    val payment_token: String,
    @Expose
    @SerializedName("source")
    val source: Source
)