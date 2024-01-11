package com.example.aroundcompose.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aroundcompose.screens.MapScreen
import com.example.aroundcompose.screens.ProfileScreen
import com.example.aroundcompose.screens.SkillsScreen
import com.example.aroundcompose.screens.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController, innerPaddings: PaddingValues) {
    val activity = (LocalContext.current as? Activity)
    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH_ROUTE,
        modifier = Modifier.padding(innerPaddings)
    ) {
        composable(Screen.SPLASH_ROUTE) {
            SplashScreen(exit = { activity?.finish() }, onNextScreen = {
                navController.popBackStack()
                navController.navigate(Screen.MAP_ROUTE)
            })
        }
        composable(Screen.MAP_ROUTE) { MapScreen() }
        composable(Screen.SKILLS_ROUTE) { SkillsScreen() }
        composable(Screen.PROFILE_ROUTE) { ProfileScreen() }
    }
}