package com.jihan.jetpack_instagram_clone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jihan.jetpack_instagram_clone.components.MyButton
import com.jihan.jetpack_instagram_clone.utils.NetworkObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ExperimentScreen(status: NetworkObserver.Status) {

    val networkStatus by rememberUpdatedState(newValue = status)

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Network Status -> $networkStatus")

            MyButton {

                CoroutineScope(Dispatchers.IO).launch {

                    SnackbarHostState().showSnackbar(
                        message = "Network Status -> $networkStatus",
                        duration = SnackbarDuration.Indefinite,
                        actionLabel = "Action",
                        withDismissAction = false
                    )
                }

            }


        }
    }


}