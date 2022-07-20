package com.example.compose.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.compose.compose.PersonCard
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun InfoScreen(navController: NavController, mViewModel: PersonViewModel) { }

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PersonInformation(persons: List<Person>) {
    LazyColumn {
        items(persons) { person ->
            PersonCard(person = person)
        }
    }
}