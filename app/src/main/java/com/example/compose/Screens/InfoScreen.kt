package com.example.compose.Screens

import android.app.Application
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.compose.PersonCard
import com.example.compose.model.MainViewModel
import com.example.compose.model.MainViewModelFactory
import com.example.compose.model.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun InfoScreen() {

    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    val persons = mViewModel.readText.observeAsState(listOf()).value

    PersonInformation(persons = persons)
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PersonInformation(persons: List<Person>) {
    LazyColumn {

        items(persons) { person ->
            PersonCard(person = person)
        }
    }
}