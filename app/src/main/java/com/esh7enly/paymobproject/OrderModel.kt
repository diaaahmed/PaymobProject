package com.esh7enly.paymobproject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("auth_token")
    @Expose
    val auth_token:String,
    @SerializedName("delivery_needed")
    @Expose
    val delivery_needed:String,
    @SerializedName("amount_cents")
    @Expose
    val amount_cents:String,
    @SerializedName("currency")
    @Expose
    val currency:String)