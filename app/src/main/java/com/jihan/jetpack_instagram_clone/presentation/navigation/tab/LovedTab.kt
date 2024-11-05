package com.jihan.jetpack_instagram_clone.presentation.navigation.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jihan.jetpack_instagram_clone.domain.utils.generateRandomColor

class LovedTab() : Tab {
    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                3u,
                "",
                null
            )
        }

    @Composable
    override fun Content() {

        Column(
            Modifier
                .fillMaxSize()
                .background(color = generateRandomColor()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Welcome Loved Tab")


        }

    }
}