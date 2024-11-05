package com.jihan.jetpack_instagram_clone.domain.sources.remote.api

import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.AuthResponse
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.LoginRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileResponse
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.SignupRequest
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserApi {

    @Multipart
    @POST("users/signup")
    suspend fun signup(
        @Part image: MultipartBody.Part?,
        @Part("data") request: SignupRequest,
    ): Response<AuthResponse>


    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @GET("users/profile")
    suspend fun getProfile() : Response<ProfileResponse>

    @Multipart
    @POST("users/profile")
    suspend fun updateProfile(
        @Part image: MultipartBody.Part?=null,
        @Part("data") request: ProfileRequest,
    ) : Response<ProfileResponse>

} 