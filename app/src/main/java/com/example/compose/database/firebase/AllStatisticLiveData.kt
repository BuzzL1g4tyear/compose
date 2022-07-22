package com.example.compose.database.firebase

import androidx.lifecycle.LiveData
import com.example.compose.model.Person
import com.example.compose.utils.EMPLOYEE
import com.example.compose.utils.NODE_STATISTIC
import com.example.compose.utils.REF_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AllStatisticLiveData : LiveData<List<Person>>() {

    val REF_STATISTIC = REF_DATABASE.child(NODE_STATISTIC).child(EMPLOYEE.Name)

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val statistics = mutableListOf<Person>()
            snapshot.children.map {
                statistics.add(it.getValue(Person::class.java) ?: Person())
            }
            value = statistics
        }

        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onActive() {
        REF_STATISTIC.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_STATISTIC.removeEventListener(listener)
        super.onInactive()
    }
}