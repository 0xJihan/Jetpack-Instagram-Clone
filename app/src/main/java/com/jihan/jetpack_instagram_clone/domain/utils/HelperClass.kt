package com.jihan.jetpack_instagram_clone.domain.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.util.Patterns
import androidx.compose.ui.graphics.Color
import com.jihan.jetpack_instagram_clone.domain.utils.Constants.BASE_URL
import com.jihan.jetpack_instagram_clone.domain.utils.Constants.TAG
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import kotlin.random.Random

object HelperClass {

    fun validateUserCredentials(
        userName: String = "Default",
        name: String = "Default",
        email: String,
        password: String,
        confirmPassword : String?=null
    ): Pair<Boolean, String> {

        val result = Pair(true, "")

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            return Pair(false, "Please Provide All Required Information")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Pair(false, "Invalid Email Address")
        } else if (password.length < 5) {
            return Pair(false, "Password should be at least 5 characters long")
        }
        else if (confirmPassword!=null && confirmPassword!=password) {
            return Pair(false, "Password doesn't match")
        }


        return result
    }

}


fun Uri?.toMultipart(context: Context): MultipartBody.Part? {
    Log.d(TAG, "toMultipart: $this")

    if (this == null) return null
    else {

        val filesDir = context.filesDir //? private files directory of the app

        val file = File(filesDir, "image.jpg")

        context.contentResolver.openInputStream(this)?.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("image", file.name, requestBody)

        return part
    }

}

fun String?.toImageUrl(): String? {

    if (this.isNullOrEmpty()) return null

        return try {
            val url = URL(BASE_URL)
            "${url.protocol}://${url.host}/$this" // Exclude   the port
        } catch (e: Exception) {
            e.printStackTrace()
            null // Return an empty string if the URL is invalid
        }

}

fun generateRandomColor(): Color {
    return Color(
        red = Random.nextFloat(), // Random float between 0 and 1
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 0.4f // You can also randomize alpha if needed
    )
}
