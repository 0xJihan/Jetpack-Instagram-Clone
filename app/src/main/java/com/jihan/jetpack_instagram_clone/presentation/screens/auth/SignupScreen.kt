package com.jihan.jetpack_instagram_clone.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.LoginRequest
import com.jihan.jetpack_instagram_clone.presentation.components.MyButton
import com.jihan.jetpack_instagram_clone.presentation.components.MyTextField
import com.jihan.jetpack_instagram_clone.ui.theme.bgColorList
import com.jihan.jetpack_instagram_clone.ui.theme.bgColorListDark
import com.jihan.jetpack_instagram_clone.domain.utils.HelperClass
import com.jihan.jetpack_instagram_clone.domain.utils.MyFonts

@Composable
fun SignupScreen(
    onLoginClicked: () -> Unit = {},
    onSignupClicked: (LoginRequest) -> Unit = {},
) {



    val bgColorList = if (isSystemInDarkTheme()) bgColorListDark else bgColorList

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var confirmPassword by remember { mutableStateOf("") }










    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = bgColorList
                )
            )
            .scrollable(rememberScrollState(0), orientation = Orientation.Vertical)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(Modifier.height(100.dp))

            Text(
                text = "Create Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                fontFamily = MyFonts.CrimsonText
            )
            Text(
                text = "to get started now!",
                fontSize = 21.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontFamily = MyFonts.CrimsonText
            )
            Spacer(Modifier.height(20.dp))








            MyTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                value = email,
                "Email Address",
                leadingIcon = {
                    Icon(imageVector = Icons.Default.MailOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, "Email Icon clicked", Toast.LENGTH_SHORT).show()
                        })
                }) {
                email = it
            }



            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = password,
                "Password",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                isPasswordFieldType = true
            ) {
                password = it
            }

            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = confirmPassword,
                "Confirm Password",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                isPasswordFieldType = true
            ) {
                confirmPassword = it
            }


            Spacer(Modifier.height(8.dp))


            Spacer(Modifier.height(60.dp))

            MyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp), cornerRadius = 15, text = "Signup"
            ) {

                val pair = HelperClass.validateUserCredentials(
                    email = email,
                    password = password, confirmPassword = confirmPassword

                    )


                //? If the validation is successful, then we will call the onSignupClicked function

                if (pair.first) {

                    onSignupClicked(LoginRequest(email, password))

                } else {
                    Toast.makeText(context, pair.second, Toast.LENGTH_SHORT).show()
                }

            }


            Box(
                modifier = Modifier.fillMaxSize(.8f), contentAlignment = Alignment.BottomCenter
            ) {

                TextButton(onClick = onLoginClicked) {
                    Text(
                        buildAnnotatedString {

                            append("Already have an account? ")

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold, color = Color.White
                                )
                            ) {
                                append("Login")
                            }
                        },
                    )
                }
            }


        }
    }
}


