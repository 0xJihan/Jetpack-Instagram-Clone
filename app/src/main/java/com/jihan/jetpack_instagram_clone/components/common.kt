package com.jihan.jetpack_instagram_clone.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jihan.jetpack_instagram_clone.R

@Composable
fun MyTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10)),
    value: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    padding: Dp = 0.dp,
    isPasswordFieldType: Boolean = false,
    onValueChange: (String) -> Unit,
) {

    val mValue by rememberUpdatedState(value)
    var mVisible by remember { mutableStateOf(false) }

    TextField(modifier = modifier.padding(padding),
        value = mValue,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(text = label, color = Color.White) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0x25FFFFFE),
            unfocusedContainerColor = Color(0x4BFFFFFF),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        leadingIcon = {
            if (leadingIcon != null) {
                leadingIcon()
            } else {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = null
                )
            }
        },
        trailingIcon = {

            if (isPasswordFieldType) {

                val icon = if (mVisible) {
                    painterResource(R.drawable.outline_visibility_24)
                } else {
                    painterResource(R.drawable.outline_visibility_off_24)
                }

                IconButton(onClick = {
                    mVisible = mVisible.not()
                }) {
                    Icon(painter = icon, contentDescription = null)
                }


            }


        },

        visualTransformation = if (isPasswordFieldType && !mVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    )
}


@Composable
fun MyButton(
    text: String = "Login",
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    elevation: Dp = 8.dp,
    cornerRadius: Int = 10,
    showProgress: Boolean = false,
    onClick: () -> Unit,
) {


    ElevatedButton(
        onClick = {
            onClick()
        },
        modifier = modifier,
        elevation = ButtonDefaults.elevatedButtonElevation(elevation),
        shape = RoundedCornerShape(cornerRadius),
        enabled = enabled
    ) {

        if (showProgress) {
            CircularProgressIndicator()
        } else Text(text)

    }
}

