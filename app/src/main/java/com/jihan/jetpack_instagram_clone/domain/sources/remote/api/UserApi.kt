package com.jihan.jetpack_instagram_clone.domain.sources.remote.api

import com.jihan.jetpack_instagram_clone.domain.models.UserRequest
import com.jihan.jetpack_instagram_clone.domain.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("users/signup")
    suspend fun signup( @Body request: UserRequest) : Response<UserResponse>

    @POST("users/login")
    suspend fun login( @Body request: UserRequest) : Response<UserResponse>


} 