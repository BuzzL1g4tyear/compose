package com.example.compose.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.compose.database.DatabaseRepo
import com.example.compose.model.Person
import com.example.compose.utils.*
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepoPerson : DatabaseRepo {

    private val mAuth = FirebaseAuth.getInstance()
    private val pathToUser = REF_DATABASE.child(NODE_USER)
    private val pathToShop = REF_DATABASE.child(NODE_SHOP)
    private val pathToPhones = REF_DATABASE.child(NODE_PHONES)

    override val readAllPerson: LiveData<List<Person>> = AllPersonsLiveData()
    override val readAllStatistics: LiveData<List<Person>> = AllStatisticLiveData()

    // TODO проверять номер
    override suspend fun create(
        person: Person,
        onSuccess: () -> Unit
    ) {

        pathToPhones.child(person.Phone).addValueEventListener(
            AppValueEventListener { task ->
                if (task.exists()) {
                    Log.d("MyTag", "create: exist")
                } else Log.d("MyTag", "not exist")
            }
        )

        mAuth.createUserWithEmailAndPassword(person.Email, person.Password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    person.id = mAuth.currentUser?.uid.toString()

                    val hashMapPerson = hashMapOf<String, Any>()
                    val hashMapShop = hashMapOf<String, Any>()

                    hashMapPerson[CHILD_ID] = person.id
                    hashMapPerson[CHILD_EMAIL] = person.Email
                    hashMapPerson[CHILD_PHONE] = person.Phone
                    hashMapPerson[CHILD_NAME] = person.Name
                    hashMapPerson[CHILD_SHOP] = person.Shop
                    hashMapPerson[CHILD_DEPARTMENT] = person.Department
                    hashMapPerson[CHILD_STATUS] = person.Status

                    hashMapShop[CHILD_ID] = person.id
                    hashMapShop[CHILD_SHOP] = person.Shop

                    pathToShop
                        .child(person.id)
                        .updateChildren(hashMapShop)

                    pathToUser
                        .child(person.Shop)
                        .child(person.id)
                        .updateChildren(hashMapPerson)
                        .addOnSuccessListener {
                            EMPLOYEE = person
                            onSuccess()
                        }
                }
            }
    }

    // TODO перед отпарвкой проверять на Совпадение номера в бд
    override suspend fun createPhoneN(person: Person, onSuccess: () -> Unit) {
        pathToPhones.child(person.Phone).addValueEventListener(
            AppValueEventListener { task ->
                if (task.exists()) {
                    Log.d("MyTag", "createPhone: exist")
                } else Log.d("MyTag", "not exist")
            }
        )

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