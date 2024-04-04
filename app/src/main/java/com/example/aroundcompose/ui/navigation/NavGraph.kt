package com.example.aroundcompose.ui.navigation

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aroundcompose.screens.ProfileScreen
import com.example.aroundcompose.screens.SkillsScreen
import com.example.aroundcompose.ui.screens.map.MapManager
import com.example.aroundcompose.ui.screens.map.MapScreen
import com.example.aroundcompose.ui.screens.map.MapViewModel
import com.example.aroundcompose.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController, innerPaddings: PaddingValues) {
    val activity = (LocalContext.current as? Activity)
    val mapViewModel = hiltViewModel<MapViewModel>()
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
        composable(Screen.MAP_ROUTE) { MapManager(mapViewModel).Create() }
        composable(Screen.SKILLS_ROUTE) { SkillsScreen() }
        composable(Screen.PROFILE_ROUTE) { ProfileScreen() }
    }
}