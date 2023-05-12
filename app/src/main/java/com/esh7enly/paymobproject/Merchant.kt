package com.esh7enly.paymobproject

data class Merchant(
    val city: String,
    val company_emails: List<Any>,
    val company_name: String,
    val country: String,
    val created_at: String,
    val id: Int,
    val phones: List<String>,
    val postal_code: String,
    val state: String,
    val street: String
)