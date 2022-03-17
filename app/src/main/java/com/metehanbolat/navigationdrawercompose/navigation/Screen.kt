package com.metehanbolat.navigationdrawercompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String) {

    sealed class DrawerScreen(route: String, title: String, val icon: ImageVector): Screen(route, title) {
        object HomeScreen: DrawerScreen("Home Screen", "Home", Icons.Filled.Home)
        object ProfileScreen: DrawerScreen("Profile Screen", "Profile", Icons.Filled.Person)
        object EmailScreen: DrawerScreen("Email Screen", "Email", Icons.Filled.Email)
        object SettingsScreen: DrawerScreen("Settings Screen", "Settings", Icons.Filled.Settings)
    }
}

val screenFromDrawer = listOf(
    Screen.DrawerScreen.HomeScreen,
    Screen.DrawerScreen.ProfileScreen,
    Screen.DrawerScreen.EmailScreen,
    Screen.DrawerScreen.SettingsScreen
)
