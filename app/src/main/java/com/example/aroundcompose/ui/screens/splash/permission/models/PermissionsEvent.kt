package com.example.aroundcompose.ui.screens.splash.permission.models

sealed class PermissionsEvent {
    object NotGranted : PermissionsEvent()
    object DismissPermission : PermissionsEvent()
}
