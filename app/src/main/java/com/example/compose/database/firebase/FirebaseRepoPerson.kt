package com.example.compose.database.firebase

import androidx.lifecycle.LiveData
import com.example.compose.database.DatabaseRepo
import com.example.compose.model.Person
import com.example.compose.utils.*
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepoPerson : DatabaseRepo {

    private val mAuth = FirebaseAuth.getInstance()
    private val pathToUser = REF_DATABASE.child(NODE_USER)
    private val pathToPhones = REF_DATABASE.child(NODE_PHONES)

    override val readAllPerson: LiveData<List<Person>> = AllPersonsLiveData()
    override val readAllStatistics: LiveData<List<Person>> = AllStatisticLiveData()

    // TODO проверять номер
    override suspend fun create(person: Person, onSuccess: () -> Unit) {

        val hashMapPersons = hashMapOf<String, Any>()

        mAuth.createUserWithEmailAndPassword(person.Email, person.Password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    person.id = mAuth.currentUser?.uid.toString()

                    hashMapPersons[CHILD_ID] = person.id
                    hashMapPersons[CHILD_EMAIL] = person.Email
                    hashMapPersons[CHILD_PHONE] = person.Phone
                    hashMapPersons[CHILD_NAME] = person.Name
                    hashMapPersons[CHILD_SHOP] = person.Shop
                    hashMapPersons[CHILD_DEPARTMENT] = person.Department
                    hashMapPersons[CHILD_STATUS] = person.Status

                    pathToUser
                        .child(person.Shop)
                        .child(person.id)
                        .updateChildren(hashMapPersons)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                }
            }
    }

    // TODO перед отпарвкой проверять на Совпадение номера в бд
    override suspend fun createPhoneN(person: Person, onSuccess: () -> Unit) {

        val hashMapPersons = hashMapOf<String, Any>()

        hashMapPersons[CHILD_PHONE] = person.Phone

        pathToPhones.child(person.Phone).updateChildren(hashMapPersons)
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