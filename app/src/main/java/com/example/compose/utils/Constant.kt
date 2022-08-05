package com.example.compose.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import com.example.compose.MainActivity
import com.example.compose.R
import com.example.compose.database.firebase.FirebaseRepoPerson
import com.example.compose.model.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi

var LIST_CONTACTS = mutableListOf<Person>()
var PERMISSION: Boolean = false
var EXIST: Boolean = false
var IS_FINISH: Boolean = false

@OptIn(ExperimentalCoroutinesApi::class)
lateinit var MAIN_ACT: MainActivity
lateinit var REPO: FirebaseRepoPerson

const val SPLASH_SCREEN = "splash_screen"
const val AUTH_SCREEN = "auth_screen"
const val MAIN_SCREEN = "main_screen"
const val CREATE_NEW_EMPLOYEE_SCREEN = "create_new_employee_screen"
const val INFO_SCREEN = "info_screen"
const val ADD_NEW_PHONE_SCREEN = "add_new_phone_screen"
