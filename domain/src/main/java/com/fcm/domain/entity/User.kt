package com.fcm.domain.entity

data class User(
    val date_joined: String,
    val email: String,
    val first_name: String,
    val groups: List<Any>,
    val id: Int,
    val is_active: Boolean,
    val is_staff: Boolean,
    val is_superuser: Boolean,
    val last_login: Any,
    val last_name: String,
    val user_permissions: List<Int>,
    val username: String
)