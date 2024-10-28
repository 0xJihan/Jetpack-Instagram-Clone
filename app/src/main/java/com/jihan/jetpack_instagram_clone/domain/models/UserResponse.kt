package com.jihan.jetpack_instagram_clone.domain.models

data class UserResponse(
    val message: String,
    val result: Result,
    val success: Boolean
)



data class Result(
    val email: String,
    val token: String
)