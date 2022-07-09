package com.example.compose.utils

import com.example.compose.MainActivity
import com.example.compose.database.firebase.FirebaseRepoPerson
import com.example.compose.model.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi

var ARRAY_CONTACTS = mutableListOf<Person>()
var PERMISSION: Boolean = false
var EXIST: Boolean = false

@OptIn(ExperimentalCoroutinesApi::class)
lateinit var MAIN_ACT: MainActivity
lateinit var REPO: FirebaseRepoPerson

const val SPLASH_SCREEN = "splash_screen"
const val AUTH_SCREEN = "auth_screen"
const val LOGIN_SCREEN = "login_screen"
const val CREATE_NEW_EMPLOYEE_SCREEN = "create_new_employee_screen"
const val INFO_SCREEN = "info_screen"
const val ADD_NEW_PHONE_SCREEN = "add_new_phone_screen"
