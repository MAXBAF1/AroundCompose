package com.example.aroundcompose.screens.splash.permission.models

import android.content.Context

sealed class PermissionsEvent {
    object NotGranted : PermissionsEvent()
    object DismissPermission : PermissionsEvent()
}
