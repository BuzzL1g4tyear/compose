package com.example.compose.Screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.compose.utils.*

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object SplashScreen : Screen(
        route = SPLASH_SCREEN,
        title = "Splash",
        icon = Icons.Default.Done
    )

    object AuthScreen : Screen(
        route = AUTH_SCREEN,
        title = "Auth",
        icon = Icons.Default.Done
    )

    object MainScreen : Screen(
        route = MAIN_SCREEN,
        title = "Main",
        icon = Icons.Default.Home
    )
    object InfoScreen : Screen(
        route = INFO_SCREEN,
        title = "Info",
        icon = Icons.Default.Info
    )

    object CreateNewEmployeeScreen : Screen(
        route = CREATE_NEW_EMPLOYEE_SCREEN,
        title = "Create",
        icon = Icons.Default.Person
    )

    object AddNewPhoneScreen : Screen(
        route = ADD_NEW_PHONE_SCREEN,
        title = "Add",
        icon = Icons.Default.Phone
    )
}