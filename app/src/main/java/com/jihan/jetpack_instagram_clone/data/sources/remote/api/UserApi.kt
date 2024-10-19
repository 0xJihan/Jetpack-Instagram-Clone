package com.jihan.jetpack_instagram_clone.data.sources.remote.api

import com.jihan.jetpack_instagram_clone.data.models.UserRequest
import com.jihan.jetpack_instagram_clone.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("users/signup")
    suspend fun signup( @Body request: UserRequest) : Response<UserResponse>

    @POST("users/login")
    suspend fun login( @Body request: UserRequest) : Response<UserResponse>


} 