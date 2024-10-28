package com.jihan.jetpack_instagram_clone.domain.repositories

import com.jihan.jetpack_instagram_clone.domain.models.UserRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.api.UserApi

class UserRepository (private val userApi: UserApi){



    suspend fun signup( userRequest: UserRequest) = userApi.signup(userRequest)



    suspend fun login( userRequest: UserRequest) = userApi.login(userRequest)



}