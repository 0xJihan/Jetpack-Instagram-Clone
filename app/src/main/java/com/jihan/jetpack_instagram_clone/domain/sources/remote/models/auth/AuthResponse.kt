package com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth

data class AuthResponse(
    val message: String,
    val success: Boolean,
    val token: String
)