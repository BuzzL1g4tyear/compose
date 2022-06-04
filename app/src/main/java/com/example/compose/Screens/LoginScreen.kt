package com.example.compose.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.model.Person
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.mobileNumberFilter

var mEmployee = Person("", "")

@Composable
fun LoginScreen(navController: NavController) {
    AddNewEmployee(
        employee = mEmployee,
        navController = navController
    )
}

@Composable
fun AddNewEmployee(employee: Person, navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputEmail(employee)
        CreateNewEmployee(navController = navController)
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
fun InputEmail(employee: Person) {
    var text by remember { mutableStateOf("") }

}

@Composable
fun CreateNewEmployee(navController: NavController) {
    Text(
        text = MAIN_ACT.getString(R.string.create_new_employee),
        modifier = Modifier.clickable {
            navController.navigate(route = Screen.CreateNewEmployeeScreen.route)
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