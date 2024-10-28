package com.jihan.jetpack_instagram_clone.domain.models


data class UserRequest(
    val email: String,
    val username : String = "",
    val password: String,
    val userProfile : String = ""
)