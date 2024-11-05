package com.jihan.jetpack_instagram_clone.presentation.screens.auth

import android.content.Context
import android.net.Uri
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.rememberAsyncImagePainter
import com.jihan.jetpack_instagram_clone.R
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.LoginRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.ProfileResponse
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.SignupRequest
import com.jihan.jetpack_instagram_clone.domain.sources.remote.models.auth.UserProfile
import com.jihan.jetpack_instagram_clone.domain.utils.Constants.TAG
import com.jihan.jetpack_instagram_clone.domain.utils.TokenManager
import com.jihan.jetpack_instagram_clone.domain.utils.UiState
import com.jihan.jetpack_instagram_clone.domain.utils.toImageUrl
import com.jihan.jetpack_instagram_clone.domain.utils.toMultipart
import com.jihan.jetpack_instagram_clone.domain.viewmodels.UserViewmodel
import com.jihan.jetpack_instagram_clone.presentation.components.CircularImage
import org.koin.compose.koinInject


data class EditProfile(
    private val onCancelCLick: () -> Unit = {},
    private val onDoneCLick: () -> Unit = {},
    private val fromSignupScreen: Boolean = false,
    private val loginRequest: LoginRequest? = null,
    private val profileResponse: ProfileResponse? = null,

    ) : Screen {
    @Composable
    override fun Content() {

        EditProfileScreen()
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun EditProfileScreen(
    ) {




        val userViewmodel = koinInject<UserViewmodel>()
        val signupResponse by userViewmodel.signupResponse.collectAsStateWithLifecycle()
        val updateProfileResponse by userViewmodel.updateProfileResponse.collectAsStateWithLifecycle()

        val result = profileResponse?.result

        var name by remember { mutableStateOf(result?.name ?: "") }
        var username by remember { mutableStateOf(result?.username ?: "") }
        var website by remember { mutableStateOf(result?.website ?: "") }
        var bio by remember { mutableStateOf(result?.bio ?: "") }

        var phone by remember { mutableStateOf(result?.phone ?: "") }
        var gender by remember { mutableStateOf(result?.gender ?: "Male") }

        var isLoading by remember { mutableStateOf(false) }

        val context = LocalContext.current

        var imageUri by remember { mutableStateOf<Uri?>(null) }




        LaunchedEffect(signupResponse) {
            when (val state = signupResponse) {
                is UiState.Loading -> {
                    isLoading = true
                }

                is UiState.Success -> {
                    isLoading = false
                    TokenManager(context).saveToken(state.data!!.token)
                    Toast.makeText(context, state.data.message, Toast.LENGTH_SHORT).show()
                    onDoneCLick()
                }

                is UiState.Error -> {
                    isLoading = false
                    Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                }

                is UiState.Initial -> {}
            }
        }


        LaunchedEffect(updateProfileResponse) {
            when (val state = updateProfileResponse) {
                is UiState.Loading -> {
                    isLoading = true
                }

                is UiState.Success -> {
                    isLoading = false
                    Toast.makeText(context, state.data!!.message, Toast.LENGTH_SHORT).show()
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
                val text = if (fromSignupScreen) "Add Profile Information" else "Edit Profile"
                Text(
                    text = text, fontWeight = FontWeight.Bold
                )
            }, navigationIcon = {
                TextButton(onClick = { onCancelCLick() }) {
                    Text("Cancel", color = MaterialTheme.colorScheme.onSurface)
                }
            }, actions = {
                TextButton(onClick = {

                    if (fromSignupScreen) {
                        isLoading = true
                        createUserAccount(
                            name,
                            username,
                            website,
                            bio,
                            phone,
                            gender,
                            userViewmodel,
                            imageUri,
                            context
                        )
                    } else {
                        updateProfile(
                            userViewmodel,
                            imageUri,
                            context,
                            name,
                            username,
                            website,
                            bio,
                            phone,
                            gender = gender
                        )
                    }


                }, enabled = isLoading.not()) {
                    Text("Done", color = Color.Blue)
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 20.dp)
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
                            ProfileImageSection(isLoading) {
                                imageUri = it
                            }
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
                                EditableField(
                                    label = "Email",
                                    value = loginRequest?.email ?: profileResponse!!.email,
                                    enabled = false
                                )
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

    private fun createUserAccount(
        name: String,
        username: String,
        website: String,
        bio: String,
        phone: String,
        gender: String,
        userViewmodel: UserViewmodel,
        imageUri: Uri?,
        context: Context,
    ) {
        val pair = validInformation(
            name = name,
            username = username,
            email = loginRequest!!.email,
        )

        if (pair.first) {
            createAccount(
                name = name,
                username = username,
                website = website,
                bio = bio,
                phone = phone,
                gender = gender,
                userRequest = loginRequest,
                userViewmodel = userViewmodel,
                imageUri = imageUri,
                context = context
            )
        } else {
            Toast.makeText(context, pair.second, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateProfile(
        userViewmodel: UserViewmodel,
        imageUri: Uri?,
        context: Context,
        name: String,
        username: String,
        website: String?,
        bio: String?,
        phone: String?,
        gender: String,
    ) {

        val imagePart = imageUri.toMultipart(context)

        val profileRequest = ProfileRequest(
            name = name,
            username = username,
            website = website,
            bio = bio,
            phone = phone,
            gender = gender
        )

        userViewmodel.updateProfile(imagePart, profileRequest)

    }


    private fun createAccount(
        name: String,
        username: String,
        website: String,
        bio: String,
        phone: String,
        gender: String,
        userRequest: LoginRequest,
        userViewmodel: UserViewmodel,
        imageUri: Uri?,
        context: Context,
    ) {

        //? converting image uri to multipart
        val imagePart = imageUri.toMultipart(context)

        val request = SignupRequest(
            email = userRequest.email, password = userRequest.password, userProfile = UserProfile(
                name = name,
                username = username,
                bio = bio,
                website = website,
                phone = phone,
                gender = gender
            )
        )

        //? requesting to create account
        userViewmodel.signup(imagePart, request)


    }


    @Composable
    private fun ProfileImageSection(isLoading: Boolean, callback: (Uri) -> Unit) {
        val loadingState by rememberUpdatedState(isLoading)

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var imageUri by remember { mutableStateOf<Uri?>(null) }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                if (it == null) return@rememberLauncherForActivityResult
                imageUri = it
                callback(it)

            }



            Log.d(TAG, "ProfileImageSection: ${imageUri?.path}")
            Log.d(TAG, "ProfileImageSection: ${profileResponse?.result?.image?.toImageUrl()}")


            val painter = rememberAsyncImagePainter(
                model = imageUri ?: profileResponse?.result?.image?.toImageUrl()?: R.drawable.img_1
            )
          CircularImage(
              modifier = Modifier.size(100.dp),
              painter
          )




            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                launcher.launch("image/*")
            }) {
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
        value: String,
        maxLines: Int = 1,
        enabled: Boolean = true,
        onValueChange: (String) -> Unit = {},
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {

            TextField(
                value = value, onValueChange = { onValueChange(it) }, enabled = enabled,
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


