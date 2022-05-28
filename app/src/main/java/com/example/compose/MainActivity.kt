package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.dp
import com.example.compose.model.Person
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.utils.mobileNumberFilter

class MainActivity : ComponentActivity() {
    private var mEmployee = Person("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                AddNewEmployee(employee = mEmployee)
            }
        }
    }

    @Composable
    fun AddNewEmployee(employee: Person) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputPhone(employee, getString(R.string.phone))
        }
        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            ButtonPress(employee = employee)
        }
    }

    @Composable
    fun InputPhone(employee: Person, hint: String) {
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
    fun ButtonPress(employee: Person) {

        FloatingActionButton(
            onClick = {

            }
        ) {

            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = getString(R.string.add_description)
            )
        }
    }
}
