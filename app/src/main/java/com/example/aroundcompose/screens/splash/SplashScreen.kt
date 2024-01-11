package com.example.aroundcompose.screens.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundcompose.screens.splash.permission.PermissionsScreen
import com.example.aroundcompose.screens.splash.permission.PermissionsViewModel

@Composable
fun SplashScreen(exit: () -> Unit, onNextScreen: () -> Unit) {
    val permissionsViewModel = hiltViewModel<PermissionsViewModel>()

    PermissionsScreen(
        viewModel = permissionsViewModel,
        onBackPressed = exit,
        onPermissionsGranted = onNextScreen
    )
}

