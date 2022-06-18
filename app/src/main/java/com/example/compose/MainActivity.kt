package com.example.compose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Screens.SetupNavGraph
import com.example.compose.model.PersonViewModel
import com.example.compose.model.PersonViewModelFactory
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.initDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFields()
        setContent {
            ComposeTheme {
                val context = LocalContext.current
                val mViewModel: PersonViewModel =
                    viewModel(
                        factory = PersonViewModelFactory(context.applicationContext as Application)
                    )
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    mViewModel = mViewModel
                )
            }
        }
    }

    private fun initFields() {
        MAIN_ACT = this@MainActivity
        initDatabase()
    }
}
