package com.jihan.jetpack_instagram_clone.domain.utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont

object MyFonts {
     val CrimsonText = FontFamily(
        Font(googleFont = GoogleFont("Crimson Text"), fontProvider = Constants.provider)
    )

    val LobsterTwo = FontFamily(
        Font(googleFont = GoogleFont("Lobster Two"), fontProvider = Constants.provider)
    )

}