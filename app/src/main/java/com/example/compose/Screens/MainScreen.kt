package com.example.compose.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose.R
import com.example.compose.compose.CircularProgressBar
import com.example.compose.model.Person
import com.example.compose.model.PersonViewModel
import com.example.compose.utils.IS_FINISH
import com.example.compose.utils.MAIN_ACT
import com.example.compose.utils.isAuthPerson
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen(navController: NavController, mViewModel: PersonViewModel) {

    val BOTTOM_ITEMS = MAIN_ACT.resources.getStringArray(R.array.items)
    val BOTTOM_ICONS = listOf(Icons.Filled.Home, Icons.Filled.Person, Icons.Filled.Phone)

    var selectedItem by remember { mutableStateOf(0) }
    var coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val persons = mViewModel.readAll().observeAsState(listOf()).value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = MAIN_ACT.getString(R.string.main_scr)) },
                actions = {
                    IconButton(onClick = {
                        mViewModel.singOut {
                            navController.navigate(Screen.AuthScreen.route)
                        }
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        FuncMainScreen(
            persons = persons,
            navController = navController
        )
    }
}

@Composable
fun FuncMainScreen(persons: List<Person>, navController: NavController) {

    Column {
        isAuthPerson { }
        PersonInformation(persons = persons)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressBar(!IS_FINISH)
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        Screen.MainScreen,
        Screen.CreateNewEmployeeScreen,
        Screen.AddNewPhoneScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDescription = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDescription: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = screen.title)
        },
        selected = currentDescription?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}