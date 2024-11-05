package com.jihan.jetpack_instagram_clone

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import com.jihan.jetpack_instagram_clone.presentation.navigation.AppRoutes
import com.jihan.jetpack_instagram_clone.ui.theme.AppTheme
import com.jihan.jetpack_instagram_clone.domain.utils.TokenManager
import com.jihan.jetpack_instagram_clone.domain.viewmodels.NavigatorViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private var isReady: Boolean = false


    private val navigatorViewmodel : NavigatorViewmodel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            splashScreen.setKeepOnScreenCondition {
//                !isReady
//            }
//
//            splashScreen.setOnExitAnimationListener { screen ->
//
//                val zoomX = ObjectAnimator.ofFloat(
//                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
//                ).setDuration(500L)
//                zoomX.interpolator = OvershootInterpolator()
//                zoomX.doOnEnd { screen.remove() }
//                zoomX.start()
//
//                val zoomY = ObjectAnimator.ofFloat(
//                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
//                ).setDuration(500L)
//                zoomY.interpolator = OvershootInterpolator()
//                zoomY.doOnEnd { screen.remove() }
//                zoomY.start()
//
//            }
//
//            CoroutineScope(Dispatchers.Main).launch {
//                delay(1000)
//                isReady = true
//            } // delay the splash screen
//        } //* showing the animated splash screen for android 12 and above


        setContent {
            AppTheme {
                val isUserLoggedIn = TokenManager(applicationContext).isLoggedIn()
                val startDestination = if (isUserLoggedIn) {
                    AppRoutes.Home
                } else {
                    AppRoutes.Next
                }


                Navigator(startDestination) {
                    navigatorViewmodel.updateNavigator(it)
                    SlideTransition(it)
                }


            }
        }


        var backPressedTime = 0L
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime < 3000) {
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext, "Press back again to exit", Toast.LENGTH_SHORT
                    ).show()
                    backPressedTime = currentTime
                }
            }
        })

    }


}



