package com.example.compose.database.firebase

import androidx.lifecycle.LiveData
import com.example.compose.model.Person
import com.example.compose.utils.EMPLOYEE
import com.example.compose.utils.IS_FINISH
import com.example.compose.utils.NODE_USER
import com.example.compose.utils.REF_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AllPersonsLiveData : LiveData<List<Person>>() {

    val REF_USERS = REF_DATABASE.child(NODE_USER).child(EMPLOYEE.Shop)

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            val persons = mutableListOf<Person>()
            snapshot.child(EMPLOYEE.Shop).children.map {
                persons.add(it.getValue(Person::class.java) ?: Person())
                if (it.key?.length == persons.size) {
                    IS_FINISH = true
                }
            }
            value = persons
        }

        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onActive() {
        REF_USERS.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_USERS.removeEventListener(listener)
        super.onInactive()
    }
}