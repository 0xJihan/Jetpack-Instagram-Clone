package com.jihan.jetpack_instagram_clone.presentation.screens.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import com.google.gson.Gson
import com.jihan.jetpack_instagram_clone.domain.models.UserProfileUpdateRequest
import com.jihan.jetpack_instagram_clone.domain.models.UserRequest
import com.jihan.jetpack_instagram_clone.domain.utils.TokenManager
import com.jihan.jetpack_instagram_clone.domain.utils.UiState
import com.jihan.jetpack_instagram_clone.domain.viewmodels.UserViewmodel
import com.jihan.jetpack_instagram_clone.presentation.components.CircularImage
import org.koin.compose.koinInject


data class EditProfile(
    private val onCancelCLick: () -> Unit = {},
    private val onDoneCLick: () -> Unit = {},
    private val fromSignupScreen: Boolean = false,
    private val userRequest: UserRequest? = null,

    ) : Screen {
    @Composable
    override fun Content() {

        if (fromSignupScreen) {
            EditProfileScreen(
                isFromLoginScreen = true,
                onCancelCLick = onCancelCLick,
                userRequest = userRequest!!
            ) {
                onDoneCLick()
            }
        } else {
            EditProfileScreen(isFromLoginScreen = false, onCancelCLick = onCancelCLick) {
            }

        }

    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun EditProfileScreen(
        onCancelCLick: () -> Unit = {},
        isFromLoginScreen: Boolean,
        userRequest: UserRequest = UserRequest(email = "", password = ""),
        onDoneCLick: () -> Unit = {}
    ) {





        val userViewmodel = koinInject<UserViewmodel>()
        val signupResponse by userViewmodel.signupResponse.collectAsStateWithLifecycle()


        var name by remember { mutableStateOf(TextFieldValue("")) }
        var username by remember { mutableStateOf(TextFieldValue(userRequest.username)) }
        var website by remember { mutableStateOf(TextFieldValue("")) }
        var bio by remember { mutableStateOf(TextFieldValue("")) }

        var email by remember { mutableStateOf(TextFieldValue(userRequest.email)) }
        var phone by remember { mutableStateOf(TextFieldValue("")) }
        var gender by remember { mutableStateOf("Male") }

        var isLoading by remember { mutableStateOf(false) }

        val context = LocalContext.current





        LaunchedEffect(signupResponse) {
            when (val state = signupResponse) {
                is UiState.Loading -> {
                    isLoading = true
                }

                is UiState.Success -> {
                    isLoading = false
                    TokenManager(context).saveToken(signupResponse.data?.result?.token)
                    Toast.makeText(context, state.data!!.message, Toast.LENGTH_SHORT).show()
                    onDoneCLick()
                }

                is UiState.Error -> {
                    isLoading = false
                    Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                }

                is UiState.Initial -> {}
            }
        }




        Scaffold(topBar = {
            TopAppBar(title = {
                val text = if (isFromLoginScreen) "Add Profile Information" else "Edit Profile"
                Text(
                    text = text, fontWeight = FontWeight.Bold
                )
            }, navigationIcon = {
                TextButton(onClick = { onCancelCLick() }) {
                    Text("Cancel", color = MaterialTheme.colorScheme.onSurface)
                }
            }, actions = {
                TextButton(onClick = {

                    val pair = validInformation(
                        name = name.text,
                        username = username.text,
                        email = email.text,
                    )

                    when {
                        pair.first -> {

                            if (fromSignupScreen){
                            createAccount(
                                name,
                                bio,
                                website,
                                email,
                                userRequest,
                                userViewmodel,
                                username
                            )}

                            else {
                                //? update profile
                            }


                        }

                        else -> {
                            Toast.makeText(context, pair.second, Toast.LENGTH_SHORT).show()
                        }
                    }


                }) {
                    Text("Done", color = Color.Blue)
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ), modifier = Modifier.padding(top = 20.dp)
            )
        }) { innerPadding ->


            Box(Modifier.fillMaxSize()) {
                LazyColumn {
                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(innerPadding)
                                .padding(16.dp)
                        ) {
                            ProfileImageSection(isLoading)
                            Spacer(modifier = Modifier.height(16.dp))

                            Column {
                                EditableField(label = "Name", value = name) { name = it }
                                EditableField(label = "Username", value = username) {
                                    username = it
                                }
                                EditableField(label = "Website", value = website) { website = it }
                                EditableField(label = "Bio", value = bio) { bio = it }
                            }


                            Spacer(modifier = Modifier.height(32.dp))



                            Column {
                                Text(
                                    text = "Private Information",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                                EditableField(label = "Email", value = email) { email = it }
                                EditableField(label = "Phone", value = phone) { phone = it }

                                GenderSelection(selectedGender = gender) {
                                    gender = it
                                }

                            }


                        }
                    }
                }
            }

        }
    }


    private fun createAccount(
        name: TextFieldValue,
        bio: TextFieldValue,
        website: TextFieldValue,
        email: TextFieldValue,
        userRequest: UserRequest,
        userViewmodel: UserViewmodel,
        username: TextFieldValue,
    ) {
        val profileRequest = UserProfileUpdateRequest(
            name = name.text,
            bio = bio.text,
            website = website.text,
            email = email.text,
            imageUrl = "https://yt3.googleusercontent.com/AxY35HWPLznD48lx67k15hEINzD2v0f4AtwoorK8qJDCOQDE7FKUHSJF4DB0VFseZLOi0NfUjg=s160-c-k-c0x00ffffff-no-rj",
            password = userRequest.password
        )

        val jsonRequest = Gson().toJson(profileRequest)

        userViewmodel.signup(
            UserRequest(
                email = email.text,
                password = "password",
                userProfile = jsonRequest,
                username = username.text
            )
        )

    }


    @Composable
    private fun ProfileImageSection(isLoading: Boolean) {
        val loadingState by rememberUpdatedState(isLoading)
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Replace with your own image resource or URL
            CircularImage(hideBorder = true, imageSize = 150)
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {}) {
                Text(
                    text = "Change Profile Photo",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(10.dp))
            if (loadingState) CircularProgressIndicator()
        }
    }


    @Composable
    private fun EditableField(
        label: String,
        value: TextFieldValue,
        maxLines: Int = 1,
        onValueChange: (TextFieldValue) -> Unit,
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {

            TextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                maxLines = maxLines,
                placeholder = { Text(text = label) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )

            )

        }
    }


    @Composable
    fun GenderSelection(
        selectedGender: String = "Male",
        onGenderSelected: (String) -> Unit,
    ) {
        Column {
            Text(text = "Select Gender", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedGender == "Male",
                    onClick = { onGenderSelected("Male") })
                Text(
                    text = "Male", modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedGender == "Female",
                    onClick = { onGenderSelected("Female") })
                Text(
                    text = "Female", modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }


    private fun validInformation(
        name: String,
        username: String,
        email: String,
    ): Pair<Boolean, String> {

        val pair = Pair(true, "Success")

        if (name.isEmpty()) {
            return Pair(false, "Name is required")
        } else if (username.isEmpty()) {
            return Pair(false, "Username is required")
        } else if (email.isEmpty()) {
            return Pair(false, "Email is required")
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return Pair(false, "Invalid Email Address")
        }


        return pair

    }
}


