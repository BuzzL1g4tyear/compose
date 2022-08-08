package com.example.compose.Screens

import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.example.compose.compose.SelectableItem
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import com.example.compose.ui.theme.Amber
import com.example.compose.ui.theme.Lite_Amber
import com.example.compose.ui.theme.White
import com.example.compose.utils.LIST_CONTACTS
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.PERMISSION
import com.example.compose.utils.initContacts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun AddNewPhone(mViewModel: PersonViewModel) {

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val scaffoldState = rememberScaffoldState()
    var buttonState by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val listOfNumber = remember {
        mutableStateListOf<Person>()
    }
    Log.d("MyTag", "PERMISSION: $PERMISSION")
    if (PERMISSION) {
        initContacts()
        Log.d("MyTag", "PERMISSION11: $PERMISSION")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.add_new_phone_scr)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressedDispatcher?.onBackPressed()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Logout")
                    }
                }
            )
        }
    ) {contentPadding ->
        Box(
            modifier = Modifier.padding(1.dp)
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 60.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Lite_Amber)
            ) {
                if (LIST_CONTACTS.isNotEmpty()) {

                    LIST_CONTACTS.sortBy {
                        it.Name
                    }

                    LIST_CONTACTS.distinct()

                    val grouped = LIST_CONTACTS.groupBy {
                        it.Name[0]
                    }

                    grouped.forEach { initial, contacts ->

                        stickyHeader {
                            Header(char = initial.toString())
                        }
                        items(contacts) { contact ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 12.dp)

                            ) {
                                var selectedState by remember { mutableStateOf(contact.StateButtonPhone) }
                                SelectableItem(
                                    selected = selectedState,
                                    title = contact.Name,
                                    subTitle = contact.Phone,
                                    onClick = {
                                        contact.StateButtonPhone = !contact.StateButtonPhone
                                        selectedState = contact.StateButtonPhone
                                        when (contact.StateButtonPhone) {
                                            true -> listOfNumber.add(contact)
                                            false -> listOfNumber.remove(contact)
                                        }
                                    }
                                )
                            }
                        }
                    }
                } else if (LIST_CONTACTS.isEmpty()) {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = MAIN_ACT.getString(R.string.empty_contacts)
                        )
                    }
                } else {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = MAIN_ACT.getString(R.string.something_wrong)
                        )
                    }
                }
            }

            val showButton = listState.firstVisibleItemIndex > 0

            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                ScrollToUpButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Amber),
                    shape = CircleShape,
                )
            }
        }

        buttonState = when (listOfNumber.isEmpty()) {
            true -> false
            false -> true
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            ButtonToAdd(
                onClick = {
                    for (person in listOfNumber) {
                        mViewModel.addPhonePerson(person = person) {}
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Amber),
                shape = CircleShape,
                enabled = buttonState
            )
        }
    }
}

@Composable
fun Header(char: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Lite_Amber, Amber, Lite_Amber
                    )
                )
            )
            .padding(
                start = 16.dp,
                top = 2.dp,
                bottom = 2.dp
            ),
        text = char,
        color = White

    )
}

@Composable
fun ScrollToUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    shape: RoundedCornerShape
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        shape = shape
    ) {
        Row(Modifier.padding(7.dp)) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "UP"
            )
        }
    }
}

@Composable
fun ButtonToAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    shape: RoundedCornerShape,
    enabled: Boolean
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        shape = shape
    ) {
        Row(Modifier.padding(7.dp)) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}