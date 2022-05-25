package com.example.compose.model

data class Person(var Name: String, var Department: String) {

    public fun getEmployeeState() = List(5) {
        it-> Person("name $it","dep $it")
    }

}