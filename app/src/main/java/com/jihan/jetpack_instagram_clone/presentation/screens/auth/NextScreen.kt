package com.jihan.jetpack_instagram_clone.presentation.screens.auth


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.jetpack_instagram_clone.R
import com.jihan.jetpack_instagram_clone.presentation.components.MyButton
import com.jihan.jetpack_instagram_clone.ui.theme.bgColorList

@Composable
fun NextScreen(
    onLoginClicked: () -> Unit = {},
    onSignupClicked: () -> Unit = {},
) {

    Column {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = bgColorList
                    )
                ), contentAlignment = Alignment.Center
        ) {


            Column {

                Spacer(Modifier.height(100.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(120.dp)
                        .padding(10.dp),

                    )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Instagram Clone",
                    style = TextStyle(
                        fontSize = 25.sp, color = Color.White, textAlign = TextAlign.Center
                    )
                )

                Spacer(Modifier.padding(50.dp))



                MyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    onLoginClicked()
                }

                OutlinedButton(
                    onClick = {
                        onSignupClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    elevation = ButtonDefaults.filledTonalButtonElevation(),
                    shape = RoundedCornerShape(8),
                ) {
                    Text("Sign Up", color = Color.White)
                }


                Spacer(Modifier.height(100.dp))


                Text(
                    "From Jihan Khan", style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp)
                )
            }
        }
    }


}




