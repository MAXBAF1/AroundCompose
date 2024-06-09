package com.example.aroundcompose.ui.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.aroundcompose.ui.screens.account.AccountScreen
import com.example.aroundcompose.ui.screens.account.AccountViewModel
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
        val accountViewModel = hiltViewModel<AccountViewModel>()

        NavHost(
            navController = navController,
            startDestination = Screens.SplashScreen.name,
            modifier = Modifier.padding(bottom = innerPaddings.calculateBottomPadding()),
            contentAlignment = Alignment.TopStart
        ) {
            composable(Screens.SplashScreen.name) { CreateSplashScreen() }
            composable(Screens.GreetingsScreen.name) { CreateGreetingsScreen() }
            composable(Screens.AuthorizationScreen.name) { CreateAuthScreen(authorizationViewModel) }
            composable(Screens.RegistrationScreen.name) {
                CreateRegistrationScreen(registrationViewModel)
            }
            composable(Screens.TeamsScreen.name) { CreateTeamsScreen(teamsViewModel) }
            composable(Screens.MapScreen.name) { CreateMapScreen(mapViewModel) }
            composable(Screens.StatisticScreen.name) { CreateStatisticsScreen(statisticsViewModel) }
            composable(Screens.SettingsScreen.name) { CreateSettingsScreen() }
            composable(Screens.FriendsScreen.name) { CreateFriendsScreen(friendsViewModel) }
            composable(
                route = "${Screens.SkillsScreen.name}?$USER_ID={$USER_ID}",
                arguments = listOf(navArgument(USER_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) { CreateSkillsScreen(skillsViewModel) }
            composable(
                route = "${Screens.AccountScreen.name}?$USER_ID={$USER_ID}",
                arguments = listOf(navArgument(USER_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) { CreateAccountScreen(accountViewModel) }
        }
    }

    @Composable
    private fun CreateMapScreen(mapViewModel: MapViewModel) {
        MapScreen(mapViewModel).Create()
    }

    @Composable
    private fun CreateSplashScreen() {
        val activity = (LocalContext.current as? Activity)
        SplashScreen(
            exit = { activity?.finish() },
            onNextScreen = {
                navController.popBackStack()
                navController.navigate(Screens.GreetingsScreen.name)
            },
        )
    }

    @Composable
    private fun CreateGreetingsScreen() {
        GreetingsScreen(toOtherScreen = { navController.navigate(it.name) { popUpTo(0) } })
    }

    @Composable
    private fun CreateSettingsScreen() {
        SettingsScreen(
            toAuthorizationScreen = {
                navController.navigate(Screens.AuthorizationScreen.name) { popUpTo(0) }
            },
            onBackClick = { navController.popBackStack() },
        ).Create()
    }

    @Composable
    private fun CreateFriendsScreen(friendsViewModel: FriendsViewModel) {
        FriendsScreen(
            viewModel = friendsViewModel,
            onBackClick = { navController.popBackStack() },
            toUserScreen = { navController.navigate("${Screens.AccountScreen.name}?$USER_ID=$it") },
        ).Create()
    }

    @Composable
    private fun CreateStatisticsScreen(statisticsViewModel: StatisticsViewModel) {
        StatisticsScreen(
            viewModel = statisticsViewModel,
            toUserScreen = { navController.navigate("${Screens.AccountScreen.name}?$USER_ID=$it") },
            onBackClicked = { navController.popBackStack() },
        ).Create()
    }

    @Composable
    private fun CreateSkillsScreen(skillsViewModel: SkillsViewModel) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments ?: return
        val userId = arguments.getInt(USER_ID, -1)

        SkillsScreen(
            viewModel = skillsViewModel,
            onBackClick = { navController.popBackStack() },
            userId = userId
        ).Create()
    }

    @Composable
    private fun CreateAccountScreen(accountViewModel: AccountViewModel) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments ?: return
        val userId = arguments.getInt(USER_ID, -1)

        AccountScreen(
            viewModel = accountViewModel,
            onBackClick = { navController.popBackStack() },
            toSettingsScreen = { navController.navigate(Screens.SettingsScreen.name) },
            toStatisticScreen = { navController.navigate(Screens.StatisticScreen.name) },
            toFriendsScreen = { navController.navigate(Screens.FriendsScreen.name) },
            toMoneysScreen = { },
            toSkillsScreen = { navController.navigate("${Screens.SkillsScreen.name}?$USER_ID=$userId") },
            userId = userId,
        ).Create()
    }

    @Composable
    private fun CreateAuthScreen(viewModel: AuthorizationViewModel) {
        AuthorizationScreen(
            viewModel = viewModel,
            onLoginClicked = { navController.navigate(Screens.MapScreen.name) },
            onRegistrationClicked = { navController.navigate(Screens.RegistrationScreen.name) },
            onForgotPasswordClicked = { navController.navigate(Screens.RestorePasswordScreen.name) },
        ).Create()
    }

    @Composable
    private fun CreateRegistrationScreen(viewModel: RegistrationViewModel) {
        RegistrationScreen(
            viewModel = viewModel,
            onNextClicked = {
                navController.navigate(Screens.TeamsScreen.name) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            },
            onBackClicked = { navController.popBackStack() },
        ).Create()
    }

    @Composable
    private fun CreateTeamsScreen(viewModel: TeamsViewModel) {
        SelectTeamScreen(
            viewModel = viewModel,
            onNextClicked = {
                navController.navigate(Screens.MapScreen.name) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
        ).Create()
    }

    companion object {
        const val USER_ID = "USER_ID"
    }
}
