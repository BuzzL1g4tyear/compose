package com.example.compose.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
        InputPhone(employee, MAIN_ACT.getString(R.string.phone))
    }
    Row(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonPress(
            employee = employee,
            navController = navController
        )
    }
}

@Composable
fun InputPhone(employee: Person, hint: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            employee.Phone = text
        },
        label = {
            Text(hint)
        },
        visualTransformation = { mobileNumberFilter(it) },
        leadingIcon = {
            Text(
                modifier = Modifier.padding(start = 5.dp, bottom = 2.dp),
                text = "+375", color = Color.Black,
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = MAIN_ACT.getString(R.string.cancel_description),
                modifier = Modifier
                    .clickable {
                        text = ""
                    }
            )
        }
    )
}

@Composable
fun ButtonPress(employee: Person, navController: NavController) {

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