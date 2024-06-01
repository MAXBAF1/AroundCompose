package com.example.aroundcompose.ui.screens.greetings.models

import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.navigation.Screen


data class GreetingsViewState(
    val team: Teams = Teams.NONE,
    val newScreen: Screen? = null
)