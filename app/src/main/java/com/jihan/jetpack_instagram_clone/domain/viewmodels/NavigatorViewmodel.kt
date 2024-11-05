package com.jihan.jetpack_instagram_clone.domain.viewmodels

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator


class NavigatorViewmodel : ViewModel() {
     lateinit var navigator: Navigator

    fun updateNavigator(navigator: Navigator) {
        this.navigator = navigator
    }
}