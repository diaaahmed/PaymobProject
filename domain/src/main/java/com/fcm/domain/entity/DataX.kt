package com.fcm.domain.entity

data class DataX(
    val agg_terminal: String,
    val amount: String,
    val bill_reference: Int,
    val biller: String,
    val due_amount: Int,
    val from_user: Any,
    val gateway_integration_pk: Int,
    val klass: String,
    val message: String,
    val ref: String,
    val rrn: String,
    val txn_response_code: String
)