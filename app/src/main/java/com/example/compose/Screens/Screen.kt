package com.example.compose.Screens

import com.example.compose.utils.CREATE_NEW_EMPLOYEE_SCREEN
import com.example.compose.utils.INFO_SCREEN
import com.example.compose.utils.LOGIN_SCREEN
import com.example.compose.utils.SPLASH_SCREEN

sealed class Screen(val route: String) {
    object SplashScreen : Screen(SPLASH_SCREEN)
    object LoginScreen : Screen(LOGIN_SCREEN)
    object CreateNewEmployeeScreen : Screen(CREATE_NEW_EMPLOYEE_SCREEN)
    object InfoScreen : Screen(INFO_SCREEN)
}
