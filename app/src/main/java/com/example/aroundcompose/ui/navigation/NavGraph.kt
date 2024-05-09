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
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.screens.account.AccountScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationViewModel
import com.example.aroundcompose.ui.screens.friends.FriendsScreen
import com.example.aroundcompose.ui.screens.friends.FriendsViewModel
import com.example.aroundcompose.ui.screens.greetings.GreetingsScreen
import com.example.aroundcompose.ui.screens.map.MapManager
import com.example.aroundcompose.ui.screens.map.MapViewModel
import com.example.aroundcompose.ui.screens.menu.MenuScreen
import com.example.aroundcompose.ui.screens.registration.RegistrationScreen
import com.example.aroundcompose.ui.screens.registration.RegistrationViewModel
import com.example.aroundcompose.ui.screens.settings.SettingsScreen
import com.example.aroundcompose.ui.screens.skills.SkillsScreen
import com.example.aroundcompose.ui.screens.skills.SkillsViewModel
import com.example.aroundcompose.ui.screens.splash.SplashScreen
import com.example.aroundcompose.ui.screens.statistics.StatisticsScreen
import com.example.aroundcompose.ui.screens.statistics.StatisticsViewModel
import com.example.aroundcompose.ui.screens.teams.SelectTeamScreen
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
        val skillsViewModel = hiltViewModel<SkillsViewModel>()
        val statisticsViewModel = hiltViewModel<StatisticsViewModel>()
        val friendsViewModel = hiltViewModel<FriendsViewModel>()

        NavHost(
            navController = navController,
            startDestination = Screen.SPLASH_ROUTE,
            modifier = Modifier.padding(innerPaddings)
        ) {
            composable(Screen.GREETINGS_ROUTE) {
                GreetingsScreen(Teams.YELLOW) { navController.navigate(Screen.AUTHORIZATION_ROUTE) }
            }
            composable(Screen.AUTHORIZATION_ROUTE) {
                CreateAuthorizationScreen(authorizationViewModel)
            }
            composable(Screen.REGISTRATION_ROUTE) { CreateRegistrationScreen(registrationViewModel) }
            composable(Screen.TEAMS_ROUTE) { CreateTeamsScreen() }
            composable(Screen.SPLASH_ROUTE) {
                SplashScreen(exit = { activity?.finish() }, onNextScreen = {
                    navController.popBackStack()
                    navController.navigate(Screen.GREETINGS_ROUTE)
                })
            }
            composable(Screen.MAP_ROUTE) { MapManager(mapViewModel).Create() }
            composable(Screen.SKILLS_ROUTE) { CreateSkillsScreen(skillsViewModel) }
            composable(Screen.STATISTICS_ROUTE) { CreateStatisticsScreen(statisticsViewModel) }
            composable(Screen.MENU_ROUTE) { CreateMenuScreen() }
            composable(Screen.ACCOUNT_ROUTE) { CreateAccountScreen() }
            composable(Screen.SETTINGS_ROUTE) { CreateSettingsScreen() }
            composable(Screen.FRIENDS_ROUTE) { CreateFriendsScreen(friendsViewModel) }
        }
    }

    @Composable
    private fun CreateSettingsScreen() {
        SettingsScreen(onBackClick = { navController.popBackStack() }).Create()
    }

    @Composable
    private fun CreateFriendsScreen(friendsViewModel: FriendsViewModel) {
        FriendsScreen(viewModel = friendsViewModel,
            onBackClick = { navController.popBackStack() },
            onMoreInfoClick = { }).Create()
    }

    @Composable
    private fun CreateStatisticsScreen(statisticsViewModel: StatisticsViewModel) {
        StatisticsScreen(
            viewModel = statisticsViewModel,
            onBackClicked = { navController.popBackStack() }).Create()
    }

    @Composable
    private fun CreateSkillsScreen(skillsViewModel: SkillsViewModel) {
        SkillsScreen(viewModel = skillsViewModel).Create()
    }

    @Composable
    private fun CreateMenuScreen() {
        MenuScreen(toSettingsScreen = {},
            toAccountScreen = { navController.navigate(Screen.ACCOUNT_ROUTE) },
            toEventsScreen = {},
            toMoneysScreen = {},
            toStatisticScreen = { navController.navigate(Screen.STATISTICS_ROUTE) },
            toFriendsScreen = { navController.navigate(Screen.FRIENDS_ROUTE) }).Create()
    }

    @Composable
    private fun CreateAccountScreen() {
        AccountScreen(onBackClick = { navController.popBackStack() },
            toSettingsScreen = { navController.navigate(Screen.SETTINGS_ROUTE) },
            toStatisticScreen = { navController.navigate(Screen.STATISTICS_ROUTE) },
            toFriendsScreen = { navController.navigate(Screen.FRIENDS_ROUTE) },
            toMoneysScreen = { }
        ).Create()
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
        SelectTeamScreen(viewModel = TeamsViewModel(), onNextClicked = {
            navController.navigate(Screen.MAP_ROUTE) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }).Create()
    }
}
