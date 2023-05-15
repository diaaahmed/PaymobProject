package com.fcm.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @Expose
    @SerializedName("identifier")
    val identifier: String,
    @Expose
    @SerializedName("subtype")
    val subtype: String
)