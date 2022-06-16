package com.example.compose.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.database.firebase.FirebaseRepo
import com.example.compose.utils.REPO

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun readAll() = REPO.readAll

    fun initDB(onSuccess: () -> Unit) {
        REPO = FirebaseRepo()
        REPO.connectToDatabase(
            {
                onSuccess()
            },
            { }
        )
    }
}

class MainViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}