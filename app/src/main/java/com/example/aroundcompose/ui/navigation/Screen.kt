package com.example.aroundcompose.ui.navigation

import com.example.aroundcompose.R

enum class Screen(val titleId: Int? = null, val iconId: Int? = null) {
    SkillsScreen(R.string.skills, R.drawable.ic_skills),
    MapScreen(R.string.map, R.drawable.ic_map),
    AccountScreen(R.string.account, R.drawable.ic_account),
    GreetingsScreen,
    SplashScreen,
    SettingsScreen,
    StatisticScreen,
    AuthorizationScreen,
    RestorePasswordScreen,
    RegistrationScreen,
    TeamsScreen,
    FriendsScreen;

    companion object {
        fun getBottomItems(): List<Screen> {
            return listOf(SkillsScreen, MapScreen, AccountScreen)
        }
    }
}
