package com.example.aroundcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aroundcompose.ui.navigation.BottomNavigation
import com.example.aroundcompose.ui.navigation.NavGraph
import com.example.aroundcompose.ui.navigation.Screen
import com.example.aroundcompose.ui.theme.AroundComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            AroundComposeTheme {
                Scaffold(bottomBar = {
                    if (currentRoute != null && currentRoute != Screen.SPLASH_ROUTE) {
                        BottomNavigation(navController = navController, currentRoute = currentRoute)
                    }
                }) { innerPaddings ->
                    NavGraph(navController = navController, innerPaddings = innerPaddings)
                }
            }
        }
    }
}

