package com.fcm.domain.entity

data class Profile(
    val acq_partner: Any,
    val activation_method: Int,
    val active: Boolean,
    val address: Any,
    val allow_encryption_bypass: Boolean,
    val allow_terminal_order_id: Boolean,
    val allow_transaction_notifications: Boolean,
    val allow_transfer_notifications: Boolean,
    val awb_banner: Any,
    val bank_deactivation_reason: Any,
    val bank_digital_rejection_reason: Any,
    val bank_merchant_digital_status: Int,
    val bank_merchant_status: Int,
    val bank_received_documents: Boolean,
    val bank_rejection_reason: Any,
    val bank_related: Any,
    val bank_staffs: BankStaffs,
    val can_bill_deposit_with_card: Boolean,
    val can_topup_merchants: Boolean,
    val city: String,
    val commercial_registration: Any,
    val commercial_registration_area: Any,
    val company_emails: List<Any>,
    val company_name: String,
    val country: String,
    val created_at: String,
    val custom_export_columns: List<Any>,
    val day_end_time: Any,
    val day_of_the_month: Any,
    val day_of_the_week: Any,
    val day_start_time: String,
    val deactivated_by_bank: Boolean,
    val delivery_status_callback: String,
    val delivery_update_endpoint: Any,
    val distributor_branch_code: Any,
    val distributor_code: Any,
    val dom: Any,
    val email_banner: Any,
    val email_notification: Boolean,
    val failed_attempts: Int,
    val filled_business_data: Boolean,
    val id: Int,
    val identification_number: Any,
    val is_mobadra: Boolean,
    val is_temp_password: Boolean,
    val latitude: Any,
    val logo_url: Any,
    val longitude: Any,
    val merchant_external_link: Any,
    val merchant_status: Int,
    val national_id: Any,
    val order_retrieval_endpoint: Any,
    val otp_sent_at: String,
    val otp_sent_to: String,
    val otp_validated_at: Any,
    val paymob_app_first_login: Any,
    val paymob_app_last_activity: Any,
    val paymob_app_merchant: Boolean,
    val payout_enabled: Boolean,
    val payout_terms: Boolean,
    val permissions: List<Any>,
    val phones: List<String>,
    val postal_code: String,
    val primary_phone_number: String,
    val primary_phone_verified: Boolean,
    val profile_type: String,
    val referral_eligible: Boolean,
    val sallefny_amount_whole: Double,
    val sallefny_fees_whole: Double,
    val sector: String,
    val server_IP: List<Any>,
    val settlement_frequency: Any,
    val signed_up_through: Int,
    val sms_sender_name: String,
    val state: String,
    val street: String,
    val super_agent: Any,
    val suspicious: Int,
    val topup_transfer_id: Any,
    val user: User,
    val username: Any,
    val wallet_limit_profile: Any,
    val wallet_phone_number: Any,
    val withhold_transfers: Boolean,
    val withhold_transfers_notes: Any,
    val withhold_transfers_reason: Any
)