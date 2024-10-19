package com.jihan.jetpack_instagram_clone.utils

import android.content.Context


class TokenManager(
      private val context: Context
) {

      private val preferences = context.getSharedPreferences(SHARED_PREFERENCE,Context.MODE_PRIVATE)


      fun saveToken(token: String?) {
            val editor = preferences.edit()
            editor.putString(TOKEN, token)
            editor.apply()
      }

      fun getToken() : String? {
            return preferences.getString(TOKEN, null)
      }

      companion object{
            const val SHARED_PREFERENCE = "TokenSharedPrefs"
            const val TOKEN = "UserToken"
      }

}