package com.example.aroundcompose.navigation

import com.example.aroundcompose.R


sealed class Screen(val titleId: Int, val iconId: Int, val route: String) {
    object MapScreen : Screen(R.string.map, R.drawable.ic_map, MAP_ROUTE)
    object SkillsScreen : Screen(R.string.skills, R.drawable.ic_skills, SKILLS_ROUTE)
    object ProfileScreen : Screen(R.string.profile, R.drawable.ic_profile, PROFILE_ROUTE)

    companion object {
        fun getBottomItems(): List<Screen> {
            return listOf(SkillsScreen, MapScreen, ProfileScreen)
        }

        const val MAIN_ROUTE = "MAIN_ROUTE"
        const val PERMISSIONS_ROUTE = "PERMISSIONS_ROUTE"
        const val MAP_ROUTE = "MAP_ROUTE"
        const val SKILLS_ROUTE = "SKILLS_ROUTE";
        const val PROFILE_ROUTE = "PROFILE_ROUTE"
    }
}
