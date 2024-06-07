package com.example.aroundcompose.ui.screens.greetings.models

import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.navigation.Screens


data class GreetingsViewState(
    val team: Teams = Teams.NONE,
    val newScreens: Screens? = null
)