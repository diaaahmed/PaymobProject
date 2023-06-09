package com.fcm.domain.entity

data class KioskResponse(
    val amount_cents: Int,
    val api_source: String,
    val captured_amount: Int,
    val created_at: String,
    val currency: String,
    val `data`: DataX,
    val error_occured: String,
    val has_parent_transaction: String,
    val id: Int,
    val integration_id: Int,
    val is_3d_secure: String,
    val is_auth: String,
    val is_capture: String,
    val is_captured: String,
    val is_hidden: String,
    val is_live: String,
    val is_refund: String,
    val is_refunded: String,
    val is_standalone_payment: String,
    val is_void: String,
    val is_voided: String,
    val merchant_staff_tag: String,
    val order: Int,
    val other_endpoint_reference: String,
    val owner: Int,
    val parent_transaction: Any,
    val payment_key_claims: PaymentKeyClaims,
    val pending: String,
    val profile_id: Int,
    val refunded_amount_cents: Int,
    val source_data: SourceData,
    val success: String,
    val transaction_processed_callback_responses: List<Any>
)