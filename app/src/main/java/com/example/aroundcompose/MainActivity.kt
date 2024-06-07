package com.example.aroundcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aroundcompose.ui.navigation.BottomNavigation
import com.example.aroundcompose.ui.navigation.NavGraph
import com.example.aroundcompose.ui.navigation.Screens
import com.example.aroundcompose.ui.theme.AroundComposeTheme
import com.example.aroundcompose.ui.theme.JetAroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val arguments = navBackStackEntry?.arguments
            val userId = arguments?.getInt(NavGraph.USER_ID, -1) ?: -1
            val showBottomNav = Screens.getBottomItems().map { it.name }
                .contains(currentRoute?.substringBefore("?")) && userId == -1

            AroundComposeTheme {
                val statusBarBg = JetAroundTheme.colors.secondaryBackground.copy(alpha = 0.2f)
                val barStyle = SystemBarStyle.light(
                    scrim = statusBarBg.toArgb(), darkScrim = statusBarBg.toArgb()
                )
                enableEdgeToEdge(statusBarStyle = barStyle)


                Scaffold(bottomBar = {
                    if (showBottomNav) {
                        BottomNavigation(
                            navController = navController,
                            listItems = Screens.getBottomItems(),
                            currentRoute = currentRoute?.substringBefore("?")!!
                        )
                    }

                }) { innerPaddings ->
                    NavGraph(navController = navController, innerPaddings = innerPaddings).Create()
                }
            }
        }
    }
}