package com.example.aroundcompose.ui.screens.teams.models

import com.example.aroundcompose.ui.common.enums.Teams

data class TeamsViewState(
    val currentTeam: Teams = Teams.NONE,
    val isEnableNextBtn: Boolean = false,
    val toNextScreen: Boolean = false,
)