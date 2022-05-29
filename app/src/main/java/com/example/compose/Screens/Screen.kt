package com.example.compose.Screens

import com.example.compose.utils.INFO_SCREEN
import com.example.compose.utils.LOGIN_SCREEN

sealed class Screen(val route: String) {
    object LoginScreen : Screen(LOGIN_SCREEN)
    object InfoScreen : Screen(INFO_SCREEN)
}
