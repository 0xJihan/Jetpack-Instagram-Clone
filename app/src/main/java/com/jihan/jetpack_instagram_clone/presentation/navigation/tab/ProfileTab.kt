package com.jihan.jetpack_instagram_clone.presentation.navigation.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jihan.jetpack_instagram_clone.presentation.components.CircularImage
import com.jihan.jetpack_instagram_clone.presentation.screens.auth.EditProfile
import com.jihan.jetpack_instagram_clone.ui.theme.AppTheme
import io.eyram.iconsax.IconSax

class ProfileTab(private val navigator: Navigator) : Tab {
    override val options: TabOptions
        @Composable get() {
            return TabOptions(
                4u, "", null
            )
        }

    @Composable
    override fun Content() {

        ProfileScreen(){
        navigator push EditProfile(onCancelCLick = {navigator.pop()})
        }

    }
}


@PreviewLightDark
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
private fun ProfileScreen(onEditCLick : () -> Unit = {}) {

    AppTheme {

        // FIXME: Column

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircularImage(imageSize = 100, hideBorder = true)
                StatsItem("54", "Posts")
                StatsItem("834", "Followers")
                StatsItem("162", "Following")
            }

            Text(
                "Jihan Khan",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 40.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                "Android developer. Expertise in Kotlin , java,php,nodeJs,mysql,sqlite,firebase,jetpack compose,api",
                modifier = Modifier.padding(start = 40.dp),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                onClick = {
                    onEditCLick()
                },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
            ) {
                Text(
                    "Edit Profile",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }


            // FIXME: Tab and Horizontal Pager

            TabHorizontalPager()

        }


    }
}

@Composable
private fun StatsItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            count,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
        Text(label, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface)
    }
}




@Composable
private fun TabHorizontalPager() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        // FIXME: Tab Items
        val tabItems = listOf(
            TabItem(
                selectedIcon = IconSax.Bold.Grid1, unselectedIcon = IconSax.Outline.Grid1
            ), TabItem(
                selectedIcon = IconSax.Bold.Personalcard,
                unselectedIcon = IconSax.Linear.Personalcard,
            )
        )

        val state = rememberPagerState(pageCount = { tabItems.size })

        var selectedIndex by remember { mutableIntStateOf(0) }

        // FIXME: Horizontal Pager

        TabRow(selectedTabIndex = selectedIndex, indicator = { }) {
            tabItems.forEachIndexed { index, tabItem ->

                // FIXME: TAB
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                    },

                    content = {
                        val icon = if (selectedIndex == index) {
                            tabItem.selectedIcon
                        } else {
                            tabItem.unselectedIcon
                        }
                        Icon(painter = painterResource(icon), contentDescription = null)
                        Spacer(Modifier.height(10.dp))
                    },
                )

            }
        }


        // FIXME: Changing pager contents on tab Item click

        LaunchedEffect(selectedIndex) {
            state.animateScrollToPage(selectedIndex)
        }

        LaunchedEffect(state.currentPage, state.isScrollInProgress) {
            if (!state.isScrollInProgress) selectedIndex = state.currentPage
        }


        // FIXME: Horizontal Pager
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), state = state
        ) { index ->

            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                when (index) {
                    0 -> Text(text = "Reels")
                    1 -> Text(text = "Posts")
                    2 -> Text(text = "Heart")
                }

            }

        }

    }
}


private data class TabItem (
    val selectedIcon : Int,
    val unselectedIcon : Int
)


