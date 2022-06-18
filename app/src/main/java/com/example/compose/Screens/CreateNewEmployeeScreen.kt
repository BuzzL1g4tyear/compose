package com.example.compose.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.compose.ShowSnackBar
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.mobileNumberFilter

val depItems = listOf(
    "IT/AV",
    "MM",
    "МБТ",
    "КБТ"
)
val statusItems = listOf(
    "Продавец",
    "Администратор",
    "Заведующий"
)

@Composable
fun CreateNewEmployeeScreen(navController: NavController, mViewModel: PersonViewModel) {

    val coroutineScope = rememberCoroutineScope()

    var vis by remember { mutableStateOf(true) }
    val expanded = remember { mutableStateOf(false) }
    val expanded1 = remember { mutableStateOf(false) }
    val statusEmployee = remember { mutableStateOf(statusItems[0]) }
    val depEmployee = remember { mutableStateOf(depItems[0]) }

    var shopEmployee by remember { mutableStateOf("Денисовская 8") }
    var emailEmployee by remember { mutableStateOf("") }
    var nameEmployee by remember { mutableStateOf("") }
    var phoneEmployee by remember { mutableStateOf("") }
    var passwordEmployee by remember { mutableStateOf("") }

    val person = Person()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//mail
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            value = emailEmployee,
            onValueChange = {
                emailEmployee = it
                person.Email = emailEmployee
            },
            label = {
                Text(MAIN_ACT.getString(R.string.email))
            },

            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = MAIN_ACT.getString(R.string.cancel_description),
                    modifier = Modifier
                        .clickable {
                            emailEmployee = ""
                        }
                )
            }
        )
//phone
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            value = phoneEmployee,
            onValueChange = {
                phoneEmployee = it
                person.Phone = phoneEmployee
            },
            label = {
                Text(MAIN_ACT.getString(R.string.phone))
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
                            phoneEmployee = ""
                        }
                )
            }
        )
// name
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            value = nameEmployee,
            onValueChange = {
                nameEmployee = it
                person.Name = passwordEmployee
            },
            label = {
                Text(MAIN_ACT.getString(R.string.name))
            },

            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = MAIN_ACT.getString(R.string.cancel_description),
                    modifier = Modifier
                        .clickable {
                            passwordEmployee = ""
                        }
                )
            }
        )
// password
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            value = passwordEmployee,
            onValueChange = {
                passwordEmployee = it
                person.Phone = passwordEmployee
            },
            label = {
                Text(MAIN_ACT.getString(R.string.password))
            },

            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = MAIN_ACT.getString(R.string.cancel_description),
                    modifier = Modifier
                        .clickable {
                            passwordEmployee = ""
                        }
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        expanded.value = !expanded.value
                    }
            ) {
                Text(text = statusEmployee.value)
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)

                DropdownMenu(expanded = expanded.value, onDismissRequest = {
                    expanded.value = false
                }) {
                    statusItems.forEach {
                        DropdownMenuItem(onClick = {
                            statusEmployee.value = it
                            expanded.value = false
                            vis = it != "Заведующий"
                            if (!vis) depEmployee.value = ""

                        }) {
                            Text(text = it)
                        }
                    }
                }
            }

            if (vis) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            expanded1.value = !expanded1.value
                        }
                ) {
                    Text(text = depEmployee.value)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)


                    DropdownMenu(expanded = expanded1.value, onDismissRequest = {
                        expanded1.value = false

                    }) {
                        depItems.forEach {
                            DropdownMenuItem(onClick = {
                                depEmployee.value = it
                                expanded1.value = false
                            }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }

        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            ShowSnackBar(
                person = person,
                mViewModel = mViewModel
            )
        }
    }
}