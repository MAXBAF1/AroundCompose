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

        /* Transparent InfoBar */
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.dark(
//                android.graphics.Color.TRANSPARENT
//            )
//        )

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            AroundComposeTheme {
                Scaffold(bottomBar = {
                    when (currentRoute) {
                        Screen.MENU_ROUTE, Screen.MAP_ROUTE, Screen.SKILLS_ROUTE -> {
                            BottomNavigation(
                                navController = navController,
                                listItems = Screen.getBottomItems(),
                                currentRoute = currentRoute
                            )
                        }
                    }
                }) { innerPaddings ->
                    NavGraph(navController = navController, innerPaddings = innerPaddings).Create()
                }
            }
        }
    }
}

