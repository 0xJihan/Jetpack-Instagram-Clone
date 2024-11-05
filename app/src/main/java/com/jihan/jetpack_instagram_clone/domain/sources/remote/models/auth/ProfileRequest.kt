package com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth

data class ProfileRequest(
    val name: String,
    val username: String,
    val bio: String?,
    val website: String?,
    val phone: String?,
    val gender: String
)