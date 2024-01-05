package com.example.aroundcompose

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aroundcompose.navigation.BottomNavigation
import com.example.aroundcompose.navigation.NavGraph
import com.example.aroundcompose.navigation.Screen
import com.example.aroundcompose.screens.PermissionsScreen
import com.example.aroundcompose.screens.checkLocationPermission
import com.example.aroundcompose.ui.theme.AroundComposeTheme
import dev.shreyaspatil.permissionflow.compose.rememberPermissionState

internal class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isGranted = checkLocationPermission()
            var showMainScreen by remember { mutableStateOf(isGranted) }

            NavHost(
                navController = rememberNavController(),
                startDestination = if (showMainScreen) Screen.MAIN_ROUTE else Screen.PERMISSIONS_ROUTE
            ) {
                composable(Screen.MAIN_ROUTE) { MainScreen() }
                composable(Screen.PERMISSIONS_ROUTE) { PermissionsScreen { showMainScreen = true } }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainScreen() {
    val navController = rememberNavController()

    AroundComposeTheme {
        Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
            NavGraph(navHostController = navController)
        }
    }
}

