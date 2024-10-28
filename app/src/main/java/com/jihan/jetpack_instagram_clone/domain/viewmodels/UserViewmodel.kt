package com.jihan.jetpack_instagram_clone.domain.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihan.jetpack_instagram_clone.domain.models.UserRequest
import com.jihan.jetpack_instagram_clone.domain.models.UserResponse
import com.jihan.jetpack_instagram_clone.domain.repositories.UserRepository
import com.jihan.jetpack_instagram_clone.domain.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject


class UserViewmodel(private val userRepository: UserRepository) : ViewModel() {

    private var _signupResponse = MutableStateFlow<UiState<UserResponse>>(UiState.Initial())
    val signupResponse = _signupResponse.asStateFlow()

    private var _loginResponse = MutableStateFlow<UiState<UserResponse>>(UiState.Initial())
    val loginResponse = _loginResponse.asStateFlow()

    // Function to handle login
    fun login(userRequest: UserRequest) = viewModelScope.launch {
        _loginResponse.emit(UiState.Loading())

        try {
            val result = userRepository.login(userRequest)

            if (result.isSuccessful && result.body() != null) {
                _loginResponse.emit(UiState.Success(result.body()!!))
            } else {
                val errorMessage =
                    JSONObject(result.errorBody()!!.charStream().readText()).getString("message")
                _loginResponse.emit(UiState.Error(errorMessage))
            }

        } catch (e: Exception) {
            _loginResponse.emit(UiState.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    // Function to handle signup
    fun signup(userRequest: UserRequest) = viewModelScope.launch {
        _signupResponse.emit(UiState.Loading())

        try {
            val result = userRepository.signup(userRequest)
            Log.wtf("Jihan Khan", result.toString())

            if (result.isSuccessful && result.body() != null) {
                _signupResponse.emit(UiState.Success(result.body()!!))
            } else {

                val errorMessage =
                    JSONObject(result.errorBody()!!.charStream().readText()).getString("message")

                _signupResponse.emit(
                    UiState.Error(errorMessage)
                )
                Log.w("UserViewModel", "Signup Error: ${result.errorBody()?.string()}")
            }

        } catch (e: Exception) {
            _signupResponse.emit(UiState.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}
