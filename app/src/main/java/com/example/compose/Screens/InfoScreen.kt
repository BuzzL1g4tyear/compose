package com.example.compose.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.compose.compose.PersonCard
import com.example.compose.model.MainViewModel
import com.example.compose.model.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun InfoScreen(navController: NavController, viewModel: MainViewModel) {

    viewModel.initDB { }

    val persons = viewModel.readAll().observeAsState(listOf()).value

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