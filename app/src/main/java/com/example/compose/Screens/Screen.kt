package com.example.compose.Screens

import com.example.compose.utils.*

sealed class Screen(val route: String) {
    object SplashScreen : Screen(SPLASH_SCREEN)
    object LoginScreen : Screen(LOGIN_SCREEN)
    object CreateNewEmployeeScreen : Screen(CREATE_NEW_EMPLOYEE_SCREEN)
    object InfoScreen : Screen(INFO_SCREEN)
    object AddNewPhoneScreen : Screen(ADD_NEW_PHONE_SCREEN)
}
