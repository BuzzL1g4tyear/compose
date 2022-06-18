package com.example.compose.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.compose.R
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import com.example.compose.utils.MAIN_ACT
import kotlinx.coroutines.launch

@Composable
fun ShowSnackBar(person: Person, mViewModel: PersonViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {

            FloatingActionButton(
                onClick = {
                    mViewModel.addPerson(person = person) {
                        mViewModel.addPerson(person = person) {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "${person.Name} ${MAIN_ACT.getString(R.string.was_created)}"
                                )
                            }
                        }
                    }
                }
            ) {
                Image(painterResource(id = R.drawable.ic_person_add), contentDescription = "")
            }
        }
    }
}
