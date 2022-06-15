package com.example.compose.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val readText: MutableLiveData<List<Person>> by lazy {
        MutableLiveData<List<Person>>()
    }

    init {
        readText.value = listOf(
            Person(
                id = "0",
                Email = "32",
                Phone = "+375",
                Name = "Valentin",
                Department = "IT",
                Status = "Прод"
            ),
            Person(
                id = "1",
                Email = "2",
                Phone = "+375",
                Name = "Egor",
                Department = "MBT",
                Status = "Прод"
            )
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