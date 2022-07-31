package com.example.compose.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.compose.database.firebase.FirebaseRepoPerson
import com.example.compose.utils.REPO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    fun readAllPersons() = REPO.readAllPerson
    fun readAllStat() = REPO.readAllStatistics

    fun initDB(onSuccess: () -> Unit) {
        REPO = FirebaseRepoPerson()
        REPO.connectToDatabase(
            {
                onSuccess()
            },
            { }
        )
    }

    fun addPerson(person: Person, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPO.create(person = person) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
        onSuccess()
    }

    fun addPhonePerson(person: Person, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPO.createPhoneN(person = person) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
        onSuccess()
    }

    fun singOut(onSuccess: () -> Unit) {
        REPO.singOut()
        onSuccess()
    }
}

class PersonViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}