package com.jihan.jetpack_instagram_clone

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jihan.jetpack_instagram_clone.ui.screens.HomeScreen
import com.jihan.jetpack_instagram_clone.ui.screens.LoginScreen
import com.jihan.jetpack_instagram_clone.ui.screens.NextScreen
import com.jihan.jetpack_instagram_clone.ui.screens.SignupScreen
import com.jihan.jetpack_instagram_clone.ui.theme.JetpackinstagramcloneTheme
import com.jihan.jetpack_instagram_clone.utils.NetworkConnectivityObserver
import com.jihan.jetpack_instagram_clone.utils.NetworkObserver
import com.jihan.jetpack_instagram_clone.utils.ScreenRoutes.HOME_SCREEN
import com.jihan.jetpack_instagram_clone.utils.ScreenRoutes.LOGIN_SCREEN
import com.jihan.jetpack_instagram_clone.utils.ScreenRoutes.NEXT_SCREEN
import com.jihan.jetpack_instagram_clone.utils.ScreenRoutes.SIGNUP_SCREEN
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isReady: Boolean = false

    @Inject
    lateinit var networkObserver: NetworkConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setKeepOnScreenCondition {
                !isReady
            }

            splashScreen.setOnExitAnimationListener { screen ->

                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
                ).setDuration(500L)
                zoomX.interpolator = OvershootInterpolator()
                zoomX.doOnEnd { screen.remove() }
                zoomX.start()

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
                ).setDuration(500L)
                zoomY.interpolator = OvershootInterpolator()
                zoomY.doOnEnd { screen.remove() }
                zoomY.start()

            }

            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                isReady = true
            } // delay the splash screen
        } // showing the animated splash screen for android 12


        setContent {
            JetpackinstagramcloneTheme {
                val status by networkObserver.Observe()
                    .collectAsStateWithLifecycle(initialValue = NetworkObserver.Status.UnAvailable)

                ExperimentScreen(status)

            }
        }
    }


    @Composable
    fun InstagramClone() {


        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = NEXT_SCREEN) {


            composable(route = NEXT_SCREEN) {
                NextScreen(onLoginClicked = {
                    navController.navigate(LOGIN_SCREEN)
                }) {
                    navController.navigate(SIGNUP_SCREEN)
                }
            }

            composable(route = LOGIN_SCREEN) {
                LoginScreen(onSignUpClicked = {

                    // checking if the previous screen is the signup screen
                    val previousScreen =
                        navController.previousBackStackEntry?.destination?.route == SIGNUP_SCREEN

                    if (previousScreen) {
                        navController.popBackStack()
                    } else {
                        navController.navigate(SIGNUP_SCREEN)
                    }

                }) {
                    navController.navigate(HOME_SCREEN) {
                        popUpTo(route = NEXT_SCREEN) {
                            inclusive = true
                        }
                    }
                }
            }

            composable(route = SIGNUP_SCREEN) {
                SignupScreen(onLoginClicked = {

                    // checking if the previous screen is the login screen
                    val previousScreen =
                        navController.previousBackStackEntry?.destination?.route == LOGIN_SCREEN
                    if (previousScreen) {
                        navController.popBackStack()
                    } else {
                        navController.navigate(LOGIN_SCREEN)
                    }
                })
            }


            composable(route = HOME_SCREEN) {
                HomeScreen()
            }


        }
    }
}

