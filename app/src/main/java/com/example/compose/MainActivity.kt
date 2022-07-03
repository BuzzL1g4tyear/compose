package com.example.compose

import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Screens.SetupNavGraph
import com.example.compose.model.PersonViewModel
import com.example.compose.model.PersonViewModelFactory
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFields()
        setContent {
            ComposeTheme {
                val context = LocalContext.current
                val mViewModel: PersonViewModel =
                    viewModel(
                        factory = PersonViewModelFactory(context.applicationContext as Application)
                    )
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    mViewModel = mViewModel
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        checkPermission(READ_CONT)
        PERMISSION = checkPermission(READ_CONT)
        CoroutineScope(Dispatchers.IO).launch {

            if (PERMISSION) {
                initContacts()
            }
        }
    }

    private fun initFields() {
        MAIN_ACT = this@MainActivity
        val mPersonViewModel = PersonViewModel(application)
        initDatabase()
        mPersonViewModel.initDB { }
        isAuthPerson {
            Log.d("MyTag", "isAuthPerson Init: in")
            Log.d("MyTag", AUTH.currentUser?.uid.toString())
            Log.d("MyTag", EMPLOYEE.id)
            Log.d("MyTag", EMPLOYEE.Shop)
        }
    }

    private fun isAuthPerson(onSuccess: () -> Unit) {
        if (AUTH.currentUser != null) {
            initUser {
                Log.d("MyTag", "isAuthPerson: true")
                onSuccess()
            }
        } else if (AUTH.currentUser == null) {
            Log.d("MyTag", "isAuthPerson: false")
        } else {
            Log.d("MyTag", "isAuthPerson: something happen")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(
                this,
                READ_CONT
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("MyTag", "onRequestPermissionsResult: ok")
        }
    }

}
