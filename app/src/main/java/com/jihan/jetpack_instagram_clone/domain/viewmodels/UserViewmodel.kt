package com.jihan.jetpack_instagram_clone.domain.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihan.jetpack_instagram_clone.domain.repositories.UserRepository
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.LoginRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.SignupRequest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class UserViewmodel(private val userRepository: UserRepository) : ViewModel() {

    val signupResponse = userRepository.signupResponse
    val loginResponse = userRepository.loginResponse

    val profileResponse = userRepository.profileResponse
    val updateProfileResponse = userRepository.updateProfileResponse

     fun signup(
        imagePart: MultipartBody.Part?,
        signupRequest: SignupRequest,
    ) {
        viewModelScope.launch {
            userRepository.signup(imagePart, signupRequest)
        }
    }


     fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            userRepository.login(loginRequest)
        }
    }


        fun getProfile() {
            viewModelScope.launch {
                userRepository.getProfile()
            }
        }


        fun updateProfile(
            imagePart: MultipartBody.Part?,
            profileRequest: ProfileRequest,
        ) {
            viewModelScope.launch {
                userRepository.updateProfile(imagePart, profileRequest)
            }
        }

}
