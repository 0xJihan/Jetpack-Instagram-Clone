package com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth

data class ProfileResponse(
    val message: String,
    val email: String,
    val result: Result
)

data class Result(
    val id: Int,
    val name: String,
    val username: String,
    val website: String?,
    val bio: String?,
    val phone: String?,
    val gender: String,
    val image: String?,
    val uid: String
)