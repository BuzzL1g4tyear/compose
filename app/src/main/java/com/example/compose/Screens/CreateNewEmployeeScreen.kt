package com.example.compose.Screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import com.example.compose.ui.theme.Amber
import com.example.compose.utils.EXIST
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.mobileNumberFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

val depItems = listOf(
    "IT AV",
    "MM",
    "МБТ",
    "КБТ"
)
val statusItems = listOf(
    "Продавец",
    "Администратор",
    "Заведующий"
)

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CreateNewEmployeeScreen(navController: NavController, mViewModel: PersonViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

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

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    //todo в условие проверки перед отправкой добавть магаз

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = (R.string.registration_scr))) },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressedDispatcher?.onBackPressed()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Logout")
                    }
                }
            )
        }
    ) { contentPadding ->
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
                singleLine = true,
                value = emailEmployee,
                onValueChange = {
                    emailEmployee = it
                    person.Email = emailEmployee
                    isButtonEnabled = emailEmployee.isNotEmpty() && nameEmployee.isNotEmpty() &&
                            phoneEmployee.isNotEmpty() && passwordEmployee.isNotEmpty()
                },
                label = {
                    Text(stringResource(id = R.string.email))
                },

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.cancel_description),
                        modifier = Modifier
                            .clickable {
                                emailEmployee = ""
                                isButtonEnabled = false
                            }
                    )
                }
            )
//phone
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true,
                value = phoneEmployee,
                onValueChange = {
                    phoneEmployee = it
                    person.Phone = phoneEmployee
                    isButtonEnabled = emailEmployee.isNotEmpty() && nameEmployee.isNotEmpty() &&
                            phoneEmployee.isNotEmpty() && passwordEmployee.isNotEmpty()
                },
                label = {
                    Text(stringResource(id = R.string.phone))
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
                        contentDescription = stringResource(id = R.string.cancel_description),
                        modifier = Modifier
                            .clickable {
                                phoneEmployee = ""
                                isButtonEnabled = false
                            }
                    )
                }
            )
// name
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                value = nameEmployee,
                onValueChange = {
                    nameEmployee = it
                    person.Name = nameEmployee
                    isButtonEnabled = emailEmployee.isNotEmpty() && nameEmployee.isNotEmpty() &&
                            phoneEmployee.isNotEmpty() && passwordEmployee.isNotEmpty()
                },
                label = {
                    Text(stringResource(id = R.string.name))
                },

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.cancel_description),
                        modifier = Modifier
                            .clickable {
                                nameEmployee = ""
                                isButtonEnabled = false
                            }
                    )
                }
            )
//shop
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                value = shopEmployee,
                onValueChange = {
                    shopEmployee = it
                    person.Shop = shopEmployee
                    isButtonEnabled = emailEmployee.isNotEmpty() && nameEmployee.isNotEmpty() &&
                            phoneEmployee.isNotEmpty() && passwordEmployee.isNotEmpty()
                },
                label = {
                    Text(stringResource(id = R.string.name))
                },

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.cancel_description),
                        modifier = Modifier
                            .clickable {
                                shopEmployee = ""
                                isButtonEnabled = false
                            }
                    )
                }
            )
// password
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                value = passwordEmployee,
                onValueChange = {
                    passwordEmployee = it
                    person.Password = passwordEmployee
                    isButtonEnabled = emailEmployee.isNotEmpty() && nameEmployee.isNotEmpty() &&
                            phoneEmployee.isNotEmpty() && passwordEmployee.isNotEmpty()
                },
                label = {
                    Text(stringResource(id = R.string.password))
                },

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.cancel_description),
                        modifier = Modifier
                            .clickable {
                                passwordEmployee = ""
                                isButtonEnabled = false
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
                            DropdownMenuItem(
                                onClick = {
                                    statusEmployee.value = it
                                    expanded.value = false

                                    vis = it != "Заведующий"
                                    if (!vis) depEmployee.value = depItems[0]

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
                person.Status = statusEmployee.value
                person.Department = depEmployee.value

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amber),
                    shape = CircleShape,
                    onClick = {
                        mViewModel.addPerson(person = person) {
                            if (!EXIST) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "${person.Name} ${MAIN_ACT.getString(R.string.was_created)}"
                                    )
                                }
                            } else if (EXIST) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "${person.Phone} ${MAIN_ACT.getString(R.string.not_added_yet)}"
                                    )
                                }
                            }
                        }
                    },
                    enabled = isButtonEnabled
                ) {
                    Row(
                        Modifier.padding(7.dp)
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_person_add),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}