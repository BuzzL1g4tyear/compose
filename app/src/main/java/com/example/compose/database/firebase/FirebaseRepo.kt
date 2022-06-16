package com.example.compose.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.compose.database.DatabaseRepo
import com.example.compose.model.Person
import com.example.compose.utils.*
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepo : DatabaseRepo {

    private val mAuth = FirebaseAuth.getInstance()
    private val path = REF_DATABASE.child(NODE_USER)

    override val readAll: LiveData<List<Person>> = AllPersonsLiveData()

    override suspend fun create(person: Person, onSuccess: () -> Unit) {

        val hashMapPersons = hashMapOf<String, Any>()

        hashMapPersons[CHILD_ID] = person.id
        hashMapPersons[CHILD_EMAIL] = person.Email
        hashMapPersons[CHILD_PHONE] = person.Phone
        hashMapPersons[CHILD_NAME] = person.Name
        hashMapPersons[CHILD_DEPARTMENT] = person.Department
        hashMapPersons[CHILD_STATUS] = person.Status

        path.child(person.id).updateChildren(hashMapPersons)
            .addOnSuccessListener {
                onSuccess()
            }
    }

    override suspend fun update(person: Person, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(person: Person, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun singOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        onSuccess()
    }
}