package com.jihan.jetpack_instagram_clone.data.repositories

import com.jihan.jetpack_instagram_clone.data.models.UserRequest
import com.jihan.jetpack_instagram_clone.data.sources.remote.api.UserApi

class UserRepository (private val userApi: UserApi){



    suspend fun signup( userRequest: UserRequest) = userApi.signup(userRequest)



    suspend fun login( userRequest: UserRequest) = userApi.login(userRequest)



}