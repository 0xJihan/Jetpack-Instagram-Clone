package com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth

data class SignupRequest(
    val email: String,
    val password: String,
    val userProfile: UserProfile
)

data class UserProfile(
    val name: String,
    val username: String,
    val bio: String,
    val website: String,
    val phone: String,
    val gender: String
)