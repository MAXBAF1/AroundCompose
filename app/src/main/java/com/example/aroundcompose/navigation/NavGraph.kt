package com.example.aroundcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aroundcompose.screens.MapScreen
import com.example.aroundcompose.screens.ProfileScreen
import com.example.aroundcompose.screens.SkillsScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.MAP_ROUTE) {
        composable(Screen.MAP_ROUTE) { MapScreen() }
        composable(Screen.SKILLS_ROUTE) { SkillsScreen() }
        composable(Screen.PROFILE_ROUTE) { ProfileScreen() }
    }
}