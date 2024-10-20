package com.jihan.jetpack_instagram_clone.utils

import android.content.Context


class TokenManager(
       context: Context
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



      fun isLoggedIn() : Boolean {
            return getToken() != null
      }


      fun deleteToken() {
            val editor = preferences.edit()
            editor.remove(TOKEN)
            editor.apply()
      }



      companion object{
            const val SHARED_PREFERENCE = "TokenSharedPrefs"
            const val TOKEN = "UserToken"
      }

}