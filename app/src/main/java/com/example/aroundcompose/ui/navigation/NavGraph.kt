package com.example.aroundcompose.ui.navigation

import android.app.Activity
import android.content.Context
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
import com.example.aroundcompose.ui.screens.account.AccountScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationViewModel
import com.example.aroundcompose.ui.screens.map.MapManager
import com.example.aroundcompose.ui.screens.map.MapViewModel
import com.example.aroundcompose.ui.screens.menu.MenuScreen
import com.example.aroundcompose.ui.screens.registration.RegistrationScreen
import com.example.aroundcompose.ui.screens.registration.RegistrationViewModel
import com.example.aroundcompose.ui.screens.splash.SplashScreen
import com.example.aroundcompose.ui.screens.teams.TeamsScreen
import com.example.aroundcompose.ui.screens.teams.TeamsViewModel


class NavGraph(
    private val navController: NavHostController,
    private val innerPaddings: PaddingValues,
) {
    @Composable
    fun Create() {
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
                CreateAuthorizationScreen(authorizationViewModel)
            }
            composable(Screen.REGISTRATION_ROUTE) { CreateRegistrationScreen(registrationViewModel) }
            composable(Screen.TEAMS_ROUTE) { CreateTeamsScreen() }
            composable(Screen.SPLASH_ROUTE) {
                SplashScreen(exit = { activity?.finish() }, onNextScreen = {
                    navController.popBackStack()
                    navController.navigate(Screen.MAP_ROUTE)
                })
            }
            composable(Screen.MAP_ROUTE) { MapManager(mapViewModel).Create() }
            composable(Screen.SKILLS_ROUTE) { SkillsScreen() }
            composable(Screen.PROFILE_ROUTE) { ProfileScreen() }
            composable(Screen.MENU_ROUTE) { CreateMenuScreen() }
            composable(Screen.ACCOUNT_ROUTE) { CreateAccountScreen() }
        }
    }

    @Composable
    private fun CreateMenuScreen() {
        MenuScreen(toSettingsScreen = {},
            toAccountScreen = { navController.navigate(Screen.ACCOUNT_ROUTE) },
            toEventsScreen = {},
            toMoneysScreen = {},
            toStatisticScreen = {},
            toFriendsScreen = {}).Create()
    }

    @Composable
    private fun CreateAccountScreen() {
        AccountScreen(onBackClick = { navController.popBackStack() },
            toSettingsScreen = { }).Create()
    }

    @Composable
    private fun CreateAuthorizationScreen(viewModel: AuthorizationViewModel) {
        AuthorizationScreen(viewModel = viewModel,
            onLoginClicked = { navController.navigate(Screen.MAP_ROUTE) },
            onRegistrationClicked = { navController.navigate(Screen.REGISTRATION_ROUTE) },
            onForgotPasswordClicked = { navController.navigate(Screen.RESTORE_PASSWORD_ROUTE) }).Create()
    }

    @Composable
    private fun CreateRegistrationScreen(viewModel: RegistrationViewModel) {
        RegistrationScreen(viewModel = viewModel, onNextClicked = {
            navController.navigate(Screen.TEAMS_ROUTE) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        }, onBackClicked = { navController.popBackStack() }).Create()
    }

    @Composable
    private fun CreateTeamsScreen() {
        TeamsScreen(viewModel = TeamsViewModel(), onNextClicked = {
            navController.navigate(Screen.MAP_ROUTE) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }).Create()
    }
}
