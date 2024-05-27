package com.example.aroundcompose.ui.screens.teams.models

data class TeamsViewState(
    val currentTeam: Int = 0,
    val isEnableNextBtn: Boolean = false,
    val toNextScreen: Boolean = false,
)