package com.jihan.jetpack_instagram_clone.utils


sealed class UiState<T>(val data: T? = null, val message: String? = null) {
    class Initial<T> : UiState<T>()
    class Loading<T> : UiState<T>()
    class Success<T>(data: T?) : UiState<T>(data)
    class Error<T>(message: String?) : UiState<T>(message = message)
}
