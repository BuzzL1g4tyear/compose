package com.example.compose.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val READ_CONT = Manifest.permission.READ_CONTACTS
const val REQ_CODE = 200

@OptIn(ExperimentalCoroutinesApi::class)
fun checkPermission(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= 23
        && ContextCompat.checkSelfPermission(
            MAIN_ACT,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(MAIN_ACT, arrayOf(permission), REQ_CODE)
        false
    } else true
}