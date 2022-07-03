package com.example.compose.Screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import com.example.compose.utils.EMPLOYEE
import com.example.compose.utils.MAIN_ACT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

lateinit var coroutineScope: CoroutineScope
lateinit var scaffoldState: ScaffoldState

@Composable
fun LoginScreen(navController: NavController, mViewModel: PersonViewModel) {

    coroutineScope = rememberCoroutineScope()
    scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Spacer(
                    Modifier.weight(1f)
                )
                IconButton(onClick = {
                    mViewModel.singOut {
                        Log.d("MyTag", "exit")
                    }
                }) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                }
            }
        }
    ) {
        FuncMainScreen(
            employee = EMPLOYEE,
            navController = navController
        )
    }
}

@Composable
fun FuncMainScreen(employee: Person, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateNewEmployee(navController = navController)
        AddNewPhone(navController = navController)
    }
    Row(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonPress(
            navController = navController
        )
    }
}

@Composable
fun CreateNewEmployee(navController: NavController) {
    Text(
        text = MAIN_ACT.getString(R.string.create_new_employee),
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate(route = Screen.CreateNewEmployeeScreen.route)
            }
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AddNewPhone(navController: NavController) {
    Text(
        text = MAIN_ACT.getString(R.string.add_new_user_phone),
        modifier = Modifier.clickable {
            navController.navigate(route = Screen.AddNewPhoneScreen.route)
        }
    )
}

@Composable
fun ButtonPress(navController: NavController) {

    FloatingActionButton(
        onClick = {
            navController.navigate(route = Screen.InfoScreen.route)
        }
    ) {

        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = MAIN_ACT.getString(R.string.add_description)
        )
    }
}