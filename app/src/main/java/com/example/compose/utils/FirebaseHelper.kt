package com.example.compose.utils

import com.example.compose.model.Person
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE: DatabaseReference
lateinit var EMPLOYEE: Person

const val NODE_SHOP = "SHOP"
const val NODE_USER = "USER"

const val CHILD_ID = "id"
const val CHILD_EMAIL = "Email"
const val CHILD_PHONE = "Phone"
const val CHILD_NAME = "Name"
const val CHILD_DEPARTMENT = "Department"
const val CHILD_STATUS = "Status"

fun initDatabase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE = Firebase.database.reference
    EMPLOYEE = Person()
    UID = AUTH.currentUser?.uid.toString()
}