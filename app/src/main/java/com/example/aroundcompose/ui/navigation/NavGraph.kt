package com.example.aroundcompose.ui.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.screens.account.AccountScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationScreen
import com.example.aroundcompose.ui.screens.authorization.AuthorizationViewModel
import com.example.aroundcompose.ui.screens.friends.FriendsScreen
import com.example.aroundcompose.ui.screens.friends.FriendsViewModel
import com.example.aroundcompose.ui.screens.greetings.GreetingsScreen
import com.example.aroundcompose.ui.screens.map.MapScreen
import com.example.aroundcompose.ui.screens.map.MapViewModel
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
    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun Create() {
        val mapViewModel = hiltViewModel<MapViewModel>()
        val authorizationViewModel = hiltViewModel<AuthorizationViewModel>()
        val registrationViewModel = hiltViewModel<RegistrationViewModel>()
        val teamsViewModel = hiltViewModel<TeamsViewModel>()
        val skillsViewModel = hiltViewModel<SkillsViewModel>()
        val statisticsViewModel = hiltViewModel<StatisticsViewModel>()
        val friendsViewModel = hiltViewModel<FriendsViewModel>()

        NavHost(
            navController = navController,
            startDestination = Screen.MAP_ROUTE,
            modifier = Modifier.padding(innerPaddings)
        ) {
            composable(Screen.SPLASH_ROUTE) { CreateSplashScreen() }
            composable(Screen.GREETINGS_ROUTE) { CreateGreetingsScreen() }
            composable(Screen.AUTHORIZATION_ROUTE) { CreateAuthScreen(authorizationViewModel) }
            composable(Screen.REGISTRATION_ROUTE) { CreateRegistrationScreen(registrationViewModel) }
            composable(Screen.TEAMS_ROUTE) {
                CreateTeamsScreen(teamsViewModel)
            }
            composable(Screen.MAP_ROUTE) { CreateMapScreen(mapViewModel) }
            composable(Screen.STATISTICS_ROUTE) { CreateStatisticsScreen(statisticsViewModel) }
            composable(Screen.SETTINGS_ROUTE) { CreateSettingsScreen() }
            composable(Screen.FRIENDS_ROUTE) { CreateFriendsScreen(friendsViewModel) }
            composable(
                route = "${Screen.SKILLS_ROUTE}?$USER_ID={$USER_ID}",
                arguments = listOf(navArgument(USER_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) { CreateSkillsScreen(skillsViewModel) }
            composable(
                route = "${Screen.ACCOUNT_ROUTE}?$USER_ID={$USER_ID}",
                arguments = listOf(navArgument(USER_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) { CreateAccountScreen() }
        }
    }

    @Composable
    private fun CreateMapScreen(mapViewModel: MapViewModel) {
        MapScreen(mapViewModel).Create()
    }

    @Composable
    private fun CreateSplashScreen() {
        val activity = (LocalContext.current as? Activity)
        SplashScreen(exit = { activity?.finish() }, onNextScreen = {
            navController.popBackStack()
            navController.navigate(Screen.GREETINGS_ROUTE)
        })
    }

    @Composable
    private fun CreateGreetingsScreen() {
        GreetingsScreen(Teams.YELLOW) { navController.navigate(Screen.AUTHORIZATION_ROUTE) }
    }

    @Composable
    private fun CreateSettingsScreen() {
        SettingsScreen(onBackClick = { navController.popBackStack() }).Create()
    }

    @Composable
    private fun CreateFriendsScreen(friendsViewModel: FriendsViewModel) {
        FriendsScreen(viewModel = friendsViewModel,
            onBackClick = { navController.popBackStack() },
            toUserScreen = {
                navController.navigate("${Screen.ACCOUNT_ROUTE}?$USER_ID=$it")
            }
        ).Create()
    }

    @Composable
    private fun CreateStatisticsScreen(statisticsViewModel: StatisticsViewModel) {
        StatisticsScreen(
            viewModel = statisticsViewModel,
            onBackClicked = { navController.popBackStack() }).Create()
    }

    @Composable
    private fun CreateSkillsScreen(skillsViewModel: SkillsViewModel) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments
        val userId = arguments?.getInt(USER_ID, -1) ?: -1

        SkillsScreen(
            viewModel = skillsViewModel,
            onBackClick = { navController.popBackStack() },
            userId = userId
        ).Create()
    }

    @Composable
    private fun CreateAccountScreen() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments
        val userId = arguments?.getInt(USER_ID, -1) ?: -1

        AccountScreen(
            onBackClick = { navController.popBackStack() },
            toSettingsScreen = { navController.navigate(Screen.SETTINGS_ROUTE) },
            toStatisticScreen = { navController.navigate(Screen.STATISTICS_ROUTE) },
            toFriendsScreen = { navController.navigate(Screen.FRIENDS_ROUTE) },
            toMoneysScreen = { },
            toSkillsScreen = { navController.navigate("${Screen.SKILLS_ROUTE}?$USER_ID=$userId") },
            userId = userId
        ).Create()
    }

    @Composable
    private fun CreateAuthScreen(viewModel: AuthorizationViewModel) {
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
    private fun CreateTeamsScreen(
        viewModel: TeamsViewModel,
    ) {
        SelectTeamScreen(
            viewModel = viewModel,
            onNextClicked = {
                navController.navigate(Screen.MAP_ROUTE) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }).Create()
    }

    companion object {
        const val USER_ID = "USER_ID"
    }
}
