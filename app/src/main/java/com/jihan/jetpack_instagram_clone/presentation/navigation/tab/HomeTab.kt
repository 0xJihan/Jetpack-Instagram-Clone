package com.jihan.jetpack_instagram_clone.presentation.navigation.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jihan.jetpack_instagram_clone.presentation.components.CircularImage
import com.jihan.jetpack_instagram_clone.presentation.components.PostImage
import com.jihan.jetpack_instagram_clone.ui.theme.AppTheme
import com.jihan.jetpack_instagram_clone.domain.utils.MyFonts
import com.jihan.jetpack_instagram_clone.domain.utils.generateRandomColor

class HomeTab(private val navigator: Navigator) : Tab {
    override val options: TabOptions
        @Composable get() {
            return TabOptions(
                0u, "", null
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

            HomeScreen()


        }

    }
}


@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}

@Composable
private fun HomeScreen() {


    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "Instagram",
            modifier = Modifier.fillMaxWidth(),
            fontFamily = MyFonts.LobsterTwo,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(10.dp))

        LazyColumn {
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(100) {
                        CircularImage(imageSize = 80, hideBorder = false)
                    }
                }
            }


            postImageItem()
        }


    }


}

private fun LazyListScope.postImageItem() {
    items(100) {
        PostImage()
    }
}




