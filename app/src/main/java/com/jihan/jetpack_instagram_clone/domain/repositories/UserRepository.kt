package com.jihan.jetpack_instagram_clone.domain.repositories

import com.jihan.jetpack_instagram_clone.domain.sources.remote.api.UserApi
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.AuthResponse
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.LoginRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileResponse
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.SignupRequest
import com.jihan.jetpack_instagram_clone.domain.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Response

class UserRepository (private val userApi: UserApi){

    private val _signupResponse = MutableStateFlow<UiState<AuthResponse>>(UiState.Initial())
    val signupResponse = _signupResponse.asStateFlow()

    private val _loginResponse = MutableStateFlow<UiState<AuthResponse>>(UiState.Initial())
    val loginResponse = _loginResponse.asStateFlow()

    private val _profileResponse = MutableStateFlow<UiState<ProfileResponse>>(UiState.Initial())
    val profileResponse = _profileResponse.asStateFlow()

    private val _updateProfileResponse = MutableStateFlow<UiState<ProfileResponse>>(UiState.Initial())
    val updateProfileResponse = _updateProfileResponse.asStateFlow()


    suspend fun signup(
        imagePart: MultipartBody.Part?,
        signupRequest: SignupRequest,
    ) {
        _signupResponse.emit(UiState.Loading())
        val response = userApi.signup(imagePart, signupRequest)

        try {
            handleResponse(response, _signupResponse)
        } catch (e: Exception) {
            _signupResponse.emit(UiState.Error(e.localizedMessage))
        }
    }


    suspend fun login(loginRequest: LoginRequest) {
        _loginResponse.emit(UiState.Loading())
        val response = userApi.login(loginRequest)

        try {
            handleResponse(response, _loginResponse)
        } catch (e: Exception) {
            _loginResponse.emit(UiState.Error(e.localizedMessage))
        }
    }



    suspend fun getProfile() {
        _profileResponse.emit(UiState.Loading())
        val response = userApi.getProfile()

        try {
            handleResponse(response, _profileResponse)
        } catch (e: Exception) {
            _profileResponse.emit(UiState.Error(e.localizedMessage))
        }
        }


    suspend fun updateProfile(imagePart: MultipartBody.Part? , profileRequest: ProfileRequest){
        _updateProfileResponse.emit(UiState.Loading())
        val response = userApi.updateProfile(image =imagePart,request = profileRequest)

        try {
            handleResponse(response, _updateProfileResponse)
        } catch (e: Exception) {
            _updateProfileResponse.emit(UiState.Error(e.localizedMessage))
        }
    }






    private suspend fun <T> handleResponse(
        response: Response<T>,
        stateFlow: MutableStateFlow<UiState<T>>,
    ) {
        if (response.isSuccessful) {
            stateFlow.emit(UiState.Success(response.body()!!))
        } else {
            val errorMessage = response.errorBody()?.let {
                JSONObject(it.charStream().readText()).getString("message")
            } ?: response.message()
            stateFlow.emit(UiState.Error(errorMessage))
        }
    }
}