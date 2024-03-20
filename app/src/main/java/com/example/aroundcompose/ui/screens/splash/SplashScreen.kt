package com.example.aroundcompose.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundcompose.ui.screens.splash.permission.PermissionsScreen
import com.example.aroundcompose.ui.screens.splash.permission.PermissionsViewModel

@Composable
fun SplashScreen(exit: () -> Unit, onNextScreen: () -> Unit) {
    val permissionsViewModel = hiltViewModel<PermissionsViewModel>()

    PermissionsScreen(
        viewModel = permissionsViewModel,
        onBackPressed = exit,
        onPermissionsGranted = onNextScreen
    )
}

