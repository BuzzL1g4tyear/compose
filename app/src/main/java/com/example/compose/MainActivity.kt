package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.model.Person
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    private var mEmployee = Person("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                Column {
                    AddNewEmployee(employee = mEmployee)
                }
            }
        }
    }

    @Composable
    fun AddNewEmployee(employee: Person) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputName(employee, getString(R.string.name))
            InputDepartment(employee, getString(R.string.department))
        }
        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            FloatingButton(employee = employee)
        }
    }

    @Composable
    fun InputName(employee: Person, hint: String) {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                employee.Name = text
            },
            label = {
                Text(hint)
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = getString(R.string.cancel_description),
                    modifier = Modifier
                        .clickable {
                            text = ""
                        }
                )
            }
        )
    }

    @Composable
    fun InputDepartment(employee: Person, hint: String) {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                employee.Department = text
            },
            label = {
                Text(hint)
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = getString(R.string.cancel_description),
                    modifier = Modifier
                        .clickable {
                            text = ""
                        }
                )
            }
        )
    }

    @Composable
    fun FloatingButton(employee: Person) {
        FloatingActionButton(
            onClick = {

            }
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = getString(R.string.add_description)
            )
        }
    }

    @Composable
    fun ShowAllEmployees(list: List<Person>) {
        LazyColumn {
            items(list) { employee ->
                ShowText(employee = employee)
            }
        }
    }

    @Composable
    fun ShowText(employee: Person) {
        Text(
            text = "${employee.Name}, ${employee.Department}",
            fontSize = 16.sp
        )
    }
}
