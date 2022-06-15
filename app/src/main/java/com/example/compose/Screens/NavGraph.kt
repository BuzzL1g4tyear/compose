package com.example.compose.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.InfoScreen.route
        ) {
            InfoScreen()
        }
        composable(
            route = Screen.CreateNewEmployeeScreen.route
        ) {
            CreateNewEmployeeScreen(navController = navController)
        }
    }
}