package com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth

data class LoginRequest(
    val email: String,
    val password: String
)