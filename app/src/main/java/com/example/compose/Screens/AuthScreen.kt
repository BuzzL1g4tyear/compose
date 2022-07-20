package com.example.compose.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.model.PersonViewModel
import com.example.compose.ui.theme.Amber
import com.example.compose.utils.AUTH
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.UID
import com.example.compose.utils.isAuthPerson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AuthScreen(navController: NavController, mViewModel: PersonViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    var emailAuth by remember {
        mutableStateOf("")
    }
    var pasAuth by remember {
        mutableStateOf("")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = MAIN_ACT.getString(R.string.auth_scr)) }
            )
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = emailAuth,
                onValueChange = {
                    emailAuth = it
                },
                label = {
                    Text(MAIN_ACT.getString(R.string.email))
                }
            )
            OutlinedTextField(
                value = pasAuth,
                onValueChange = {
                    pasAuth = it
                },
                label = {
                    Text(MAIN_ACT.getString(R.string.password))
                }
            )
            Text(
                text = MAIN_ACT.getString(R.string.no_account_yet),
                Modifier.clickable {
                    navController.navigate(
                        route = Screen.CreateNewEmployeeScreen.route
                    )
                }
            )
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                ButtonToSingIn(
                    onClick = {
                        AUTH.signInWithEmailAndPassword(
                            emailAuth,
                            pasAuth
                        ).addOnSuccessListener {
                            isAuthPerson {
                                UID = AUTH.currentUser?.uid.toString()
                                navController.navigate(route = Screen.MainScreen.route)
                            }
                        }.addOnFailureListener {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = MAIN_ACT.getString(R.string.something_incorrectly)
                                )
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amber),
                    shape = CircleShape,
                    enabled = emailAuth.isNotEmpty() && pasAuth.isNotEmpty()
                )
            }
        }
    }
}

@Composable
fun ButtonToSingIn(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    shape: RoundedCornerShape,
    enabled: Boolean
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        shape = shape
    ) {
        Row(Modifier.padding(7.dp)) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Add"
            )
        }
    }
}