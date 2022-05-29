package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Screens.SetupNavGraph
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.utils.MAIN_ACT

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFields()
        setContent {
            ComposeTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }

    private fun initFields() {
        MAIN_ACT = this
    }
}
