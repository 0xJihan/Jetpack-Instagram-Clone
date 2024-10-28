package com.jihan.jetpack_instagram_clone.domain.utils

import androidx.compose.ui.text.googlefonts.GoogleFont
import com.jihan.jetpack_instagram_clone.R

object Constants {
    const val BASE_URL = "http://192.168.2.100:5000"
    const val TAG = "Jihan Khan"



    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
}
