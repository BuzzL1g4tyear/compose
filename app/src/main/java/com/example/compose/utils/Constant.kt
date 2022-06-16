package com.example.compose.utils

import com.example.compose.MainActivity
import com.example.compose.database.DatabaseRepo
import com.example.compose.database.firebase.FirebaseRepo

lateinit var MAIN_ACT: MainActivity
lateinit var REPO: FirebaseRepo

const val SPLASH_SCREEN = "splash_screen"
const val LOGIN_SCREEN = "login_screen"
const val CREATE_NEW_EMPLOYEE_SCREEN = "create_new_employee_screen"
const val INFO_SCREEN = "info_screen"
