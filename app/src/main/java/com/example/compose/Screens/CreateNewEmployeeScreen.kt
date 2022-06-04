package com.example.compose.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import com.example.compose.model.Person
import com.example.compose.utils.*
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


@Composable
fun CreateNewEmployeeScreen(navController: NavController) {
    var emailEmployee by remember { mutableStateOf("") }
    var nameEmployee by remember { mutableStateOf("") }
    var depEmployee by remember { mutableStateOf("") }
    var phoneEmployee by remember { mutableStateOf("") }
    var statusEmployee by remember { mutableStateOf("") }
    var passwordEmployee by remember { mutableStateOf("") }

    val employee = Person()

    fun addEmployee() {

        AUTH.createUserWithEmailAndPassword(emailEmployee, passwordEmployee)
            .addOnCompleteListener(MAIN_ACT) { task ->
                if (task.isSuccessful) {
                    val uId = AUTH.currentUser?.uid.toString()
                    val dataMap = mutableMapOf<String, Any>()
                    dataMap[CHILD_ID] = uId
                    dataMap[CHILD_EMAIL] = emailEmployee
                    dataMap[CHILD_PHONE] = phoneEmployee
                    dataMap[CHILD_NAME] = nameEmployee
                    dataMap[CHILD_DEPARTMENT] = depEmployee
                    dataMap[CHILD_STATUS] = statusEmployee
                    REF_DATABASE.child(NODE_USER).child(uId).updateChildren(dataMap)
                }
            }
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16).padStart(32, '0')
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            value = emailEmployee,
            onValueChange = {
                emailEmployee = it
                employee.Email = emailEmployee
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

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            value = phoneEmployee,
            onValueChange = {
                phoneEmployee = it
                employee.Phone = phoneEmployee
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
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            value = passwordEmployee,
            onValueChange = {
                passwordEmployee = it
                employee.Phone = passwordEmployee
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
        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                onClick = {
                    addEmployee()
                }
            ) {
                Image(painterResource(id = R.drawable.ic_person_add), contentDescription = "")
            }

        }
    }
}