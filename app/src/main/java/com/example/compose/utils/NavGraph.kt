package com.example.compose.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.model.MainViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mViewModel: MainViewModel
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
            InfoScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(
            route = Screen.CreateNewEmployeeScreen.route
        ) {
            CreateNewEmployeeScreen(navController = navController)
        }
    }
}