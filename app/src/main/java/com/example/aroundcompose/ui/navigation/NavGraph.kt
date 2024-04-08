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
import com.example.aroundcompose.ui.screens.authorization.AuthorizationScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationViewModel
import com.example.aroundcompose.ui.screens.map.MapScreen
import com.example.aroundcompose.ui.screens.map.MapViewModel
import com.example.aroundcompose.ui.screens.registration.RegistrationScreen
import com.example.aroundcompose.ui.screens.registration.RegistrationViewModel
import com.example.aroundcompose.ui.screens.splash.SplashScreen
import com.example.aroundcompose.ui.screens.teams.TeamsScreen
import com.example.aroundcompose.ui.screens.teams.TeamsViewModel

@Composable
fun NavGraph(navController: NavHostController, innerPaddings: PaddingValues) {
    val activity = (LocalContext.current as? Activity)
    val mapViewModel = hiltViewModel<MapViewModel>()
    val authorizationViewModel = hiltViewModel<AuthorizationViewModel>()
    val registrationViewModel = hiltViewModel<RegistrationViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.AUTHORIZATION_ROUTE,
        modifier = Modifier.padding(innerPaddings)
    ) {
        composable(Screen.AUTHORIZATION_ROUTE) {
            AuthorizationScreen(
                viewModel = authorizationViewModel,
                onLoginClicked = { navController.navigate(Screen.MAP_ROUTE) },
                onRegistrationClicked = { navController.navigate(Screen.REGISTRATION_ROUTE) },
                onForgotPasswordClicked = { navController.navigate(Screen.RESTORE_PASSWORD_ROUTE) }
            ).Create()
        }
        composable(Screen.REGISTRATION_ROUTE) {
            RegistrationScreen(
                viewModel = registrationViewModel,
                onNextClicked = {
                    navController.navigate(Screen.TEAMS_ROUTE) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onBackClicked = { navController.popBackStack() }
            ).Create()
        }
        composable(Screen.TEAMS_ROUTE) {
            TeamsScreen(
                viewModel = TeamsViewModel(),
                onNextClicked = {
                    navController.navigate(Screen.MAP_ROUTE) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            ).Create()
        }
        composable(Screen.SPLASH_ROUTE) {
            SplashScreen(
                exit = { activity?.finish() },
                onNextScreen = {
                    navController.popBackStack()
                    navController.navigate(Screen.MAP_ROUTE)
                }
            )
        }
        composable(Screen.MAP_ROUTE) { MapScreen(mapViewModel).Create() }
        composable(Screen.SKILLS_ROUTE) { SkillsScreen() }
        composable(Screen.PROFILE_ROUTE) { ProfileScreen() }
    }
}