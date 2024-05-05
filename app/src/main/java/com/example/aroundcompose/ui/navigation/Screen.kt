package com.example.aroundcompose.ui.navigation

import com.example.aroundcompose.R

sealed class Screen(val titleId: Int, val iconId: Int, val route: String) {
    object MenuScreen : Screen(R.string.menu, R.drawable.ic_menu, MENU_ROUTE)
    object MapScreen : Screen(R.string.map, R.drawable.ic_map, MAP_ROUTE)
    object SkillsScreen : Screen(R.string.skills, R.drawable.ic_skills, SKILLS_ROUTE)


    companion object {
        fun getBottomItems(): List<Screen> {
            return listOf(MenuScreen, MapScreen, SkillsScreen)
        }

        const val GREETINGS_ROUTE = "GREETINGS_ROUTE"
        const val SPLASH_ROUTE = "SPLASH_ROUTE"
        const val MAIN_ROUTE = "MAIN_ROUTE"
        const val PERMISSIONS_ROUTE = "PERMISSIONS_ROUTE"
        const val MAP_ROUTE = "MAP_ROUTE"
        const val MENU_ROUTE = "MENU_ROUTE"
        const val ACCOUNT_ROUTE = "ACCOUNT_ROUTE"
        const val SKILLS_ROUTE = "SKILLS_ROUTE";
        const val STATISTICS_ROUTE = "STATISTICS_ROUTE";
        const val PROFILE_ROUTE = "PROFILE_ROUTE"
        const val AUTHORIZATION_ROUTE = "AUTHORIZATION_ROUTE"
        const val RESTORE_PASSWORD_ROUTE = "RESTORE_PASSWORD_ROUTE"
        const val REGISTRATION_ROUTE = "REGISTRATION_ROUTE"
        const val TEAMS_ROUTE = "TEAMS_ROUTE"
        const val FRIENDS_ROUTE = "FRIENDS_ROUTE"
    }
}
