package com.jihan.jetpack_instagram_clone.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jihan.jetpack_instagram_clone.presentation.screens.auth.EditProfile
import com.jihan.jetpack_instagram_clone.presentation.screens.auth.LoginScreen
import com.jihan.jetpack_instagram_clone.presentation.screens.auth.NextScreen
import com.jihan.jetpack_instagram_clone.presentation.screens.auth.SignupScreen
import com.jihan.jetpack_instagram_clone.presentation.screens.main.HomeScreen

object AppRoutes {


    object Next : Screen {
        private fun readResolve(): Any = Login

        @Composable
        override fun Content() {


            val navigator = LocalNavigator.currentOrThrow

            NextScreen(onLoginClicked = { navigator push Login }) {
                navigator push Signup
            }
        }

    }

    object Login : Screen {
        private fun readResolve(): Any = Login

        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow
            LoginScreen(onSignUpClicked = {
                val lastIndex = navigator.items.size - 2
                if (navigator.items[lastIndex] is Signup) {
                    navigator.pop()
                } else {
                    navigator push Signup
                }
            }) {
                navigator.replaceAll(Home)
            }
        }

    }


    object Signup : Screen {
        private fun readResolve(): Any = Login


        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow

            SignupScreen(onLoginClicked = {
                val loginBackStack = navigator.items.size - 2

                if (navigator.items[loginBackStack] is Login) {
                    navigator.pop()
                } else {
                    navigator push Login
                }

            }) {
                navigator push EditProfile(
                    onCancelCLick = { navigator.pop() },
                    onDoneCLick = { navigator replaceAll Home },
                    fromSignupScreen = true,
                    userRequest = it
                )
            }
        }

    }

    object Home : Screen {
        private fun readResolve(): Any = Home

        @Composable
        override fun Content() {
            HomeScreen()
        }

    }
}


