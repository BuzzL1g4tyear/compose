package com.example.compose.database

import androidx.lifecycle.LiveData
import com.example.compose.model.Person

interface DatabaseRepo {

    val readAllPerson: LiveData<List<Person>>
    val readAllStatistics: LiveData<List<Person>>

    suspend fun create(person: Person, onSuccess: () -> Unit)

    suspend fun update(person: Person, onSuccess: () -> Unit)

    suspend fun delete(person: Person, onSuccess: () -> Unit)

    fun singOut() {}

    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {}
}