package com.jihan.jetpack_instagram_clone.domain.models

data class UserProfileUpdateRequest (
    val name: String,
    val bio: String ="",
    val website : String = "",
    val email: String = "",
    val imageUrl: String = "",
    val phone: String = "",
    val password : String = ""
)