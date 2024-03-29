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
const val NODE_PHONES = "PHONES"
const val NODE_USER = "USER"
const val NODE_STATISTIC = "STATISTIC"

const val CHILD_ID = "id"
const val CHILD_EMAIL = "Email"
const val CHILD_SHOP = "Shop"
const val CHILD_PHONE = "Phone"
const val CHILD_NAME = "Name"
const val CHILD_DEPARTMENT = "Department"
const val CHILD_STATUS = "Status"
const val CHILD_PERSON_NAME = "PersonName"
const val CHILD_AMOUNT_OF_DEALS = "AmountOfDeals"
const val CHILD_AMOUNT_ITEMS_IN_DEAL = "AmountItemsInDeal"

fun initDatabase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE = Firebase.database.reference
    EMPLOYEE = Person()
    UID = AUTH.currentUser?.uid.toString()
}

inline fun initUser(crossinline function: () -> Unit) {
    REF_DATABASE.child(NODE_SHOP).child(UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            EMPLOYEE = it.getValue(Person::class.java) ?: Person()

            REF_DATABASE.child(NODE_USER).child(EMPLOYEE.Shop).child(EMPLOYEE.id)
                .addListenerForSingleValueEvent(AppValueEventListener { user ->
                    EMPLOYEE = user.getValue(Person::class.java) ?: Person()
                })

            function()
        })
}