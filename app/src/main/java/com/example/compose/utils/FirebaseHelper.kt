package com.example.compose.utils

import com.example.compose.model.Person
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE: DatabaseReference
lateinit var EMPLOYEE: Person

const val NODE_SHOP = "SHOP"
const val NODE_USER = "USER"

const val CHILD_ID = "ID"
const val CHILD_EMAIL = "EMAIL"
const val CHILD_PHONE = "PHONE"
const val CHILD_NAME = "NAME"
const val CHILD_DEPARTMENT = "DEPARTMENT"
const val CHILD_STATUS = "STATUS"