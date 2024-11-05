package com.jihan.jetpack_instagram_clone.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.jihan.jetpack_instagram_clone.domain.viewmodels.NavigatorViewmodel
import com.jihan.jetpack_instagram_clone.presentation.navigation.tab.AddTab
import com.jihan.jetpack_instagram_clone.presentation.navigation.tab.HomeTab
import com.jihan.jetpack_instagram_clone.presentation.navigation.tab.LovedTab
import com.jihan.jetpack_instagram_clone.presentation.navigation.tab.ProfileTab
import com.jihan.jetpack_instagram_clone.presentation.navigation.tab.SearchTab
import io.eyram.iconsax.IconSax
import org.koin.compose.koinInject

@Composable
fun HomeScreen() {



    // ?:  TabNavigator for Bottom Navigation
    TabNavigator(HomeTab()) {

        Scaffold(bottomBar = {
            BottomAppBar(modifier = Modifier.navigationBarsPadding()) {
                TabNavigationItem(HomeTab(), BottomTabIcon.HOME)
                TabNavigationItem(SearchTab(), BottomTabIcon.SEARCH)
                TabNavigationItem(AddTab(), BottomTabIcon.ADD)
                TabNavigationItem(LovedTab(), BottomTabIcon.LOVED)
                TabNavigationItem(ProfileTab(), BottomTabIcon.PROFILE)
            }
        }) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                CurrentTab()

            }
        }

    }


}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab, icon: BottomTabIcon) {

    val iconRes =
        if (LocalTabNavigator.current.current == tab) icon.selectedIcon else icon.unselectedIcon

    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(selected = tabNavigator.current == tab, onClick = {
        tabNavigator.current = tab
    }, label = {
        Text(tab.options.title)
    }, icon = {
        Icon(painter = painterResource(iconRes), contentDescription = tab.options.title)
    },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primary,
            ),
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background))


}


private enum class BottomTabIcon(val selectedIcon: Int, val unselectedIcon: Int) {
    HOME(
        selectedIcon = IconSax.Bold.Home,
        unselectedIcon = IconSax.Outline.Home
    ),

    SEARCH(
        selectedIcon = IconSax.Bold.SearchNormal,
        unselectedIcon = IconSax.Outline.SearchNormal
    ),
    PROFILE(
        selectedIcon = IconSax.Bold.User,
        unselectedIcon = IconSax.Outline.User
    ),
    ADD(
        selectedIcon = IconSax.Bold.Add,
        unselectedIcon = IconSax.Outline.Add
    ),
    LOVED(
        selectedIcon = IconSax.Bold.Heart,
        unselectedIcon = IconSax.Outline.Heart
    ),

}

