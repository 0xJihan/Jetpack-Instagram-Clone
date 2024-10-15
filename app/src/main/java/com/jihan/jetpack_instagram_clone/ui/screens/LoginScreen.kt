package com.jihan.jetpack_instagram_clone.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.jetpack_instagram_clone.components.MyButton
import com.jihan.jetpack_instagram_clone.components.MyTextField
import com.jihan.jetpack_instagram_clone.ui.theme.bgColorList
import com.jihan.jetpack_instagram_clone.utils.HelperClass
import com.jihan.jetpack_instagram_clone.utils.MyFonts

@Composable
fun LoginScreen(
    onSignUpClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {}
) {


    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = bgColorList
                )
            )
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
                text = "Welcome,",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                fontFamily = MyFonts.CrimsonText
            )
            Text(
                text = "Glad to see you",
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
                        imageVector = Icons.Default.Lock, contentDescription = null
                    )
                },
                isPasswordFieldType = true
            ) {
                password = it
            }


            Spacer(Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Forgot Password? ", color = Color.Black
                )


                Text(text = "Click here", fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.clickable {
                        // TODO: Forgot Password
                    } // Make it clickable
                )
            }


            Spacer(Modifier.height(60.dp))

            MyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp), cornerRadius = 15
            ) {
                val pair = HelperClass.validateUserCredentials(email = email, password = password)

               if (pair.first){
                   Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                    onLoginClicked()
               }
                else {
                    Toast.makeText(context, pair.second, Toast.LENGTH_SHORT).show()
                }


            }


            Box(
                modifier = Modifier.fillMaxSize(.8f), contentAlignment = Alignment.BottomCenter
            ) {

                Row {

                    Text(
                        "Don't have an account?  ", style = TextStyle(
                            fontFamily = MyFonts.CrimsonText
                        )
                    )
                    Text("Signup now", style = TextStyle(
                        color = Color.White, fontFamily = MyFonts.CrimsonText
                    ), modifier = Modifier.clickable {
                      onSignUpClicked()
                    })
                }
            }


        }
    }


}


@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    LoginScreen()
}