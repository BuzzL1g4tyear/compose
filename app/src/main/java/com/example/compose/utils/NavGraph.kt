package com.example.compose.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.model.PersonViewModel
import com.example.compose.utils.AUTH

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mViewModel: PersonViewModel
) {
    NavHost(
        navController = navController,
        startDestination = if (AUTH.currentUser != null) {
            Screen.MainScreen.route
        } else {
            Screen.AuthScreen.route
        }

    ) {
        composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(
                navController = navController,
                mViewModel = mViewModel
            )
        }
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(
                navController = navController,
                mViewModel = mViewModel
            )
        }
        composable(
            route = Screen.InfoScreen.route
        ) {
            InfoScreen(
                navController = navController,
                mViewModel = mViewModel
            )
        }
        composable(
            route = Screen.CreateNewEmployeeScreen.route
        ) {
            CreateNewEmployeeScreen(
                navController = navController,
                mViewModel = mViewModel
            )
        }
        composable(
            route = Screen.AddNewPhoneScreen.route
        ) {
            AddNewPhone(
                mViewModel = mViewModel
            )
        }
    }
}