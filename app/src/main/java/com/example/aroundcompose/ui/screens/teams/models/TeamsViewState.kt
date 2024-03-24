package com.example.aroundcompose.ui.screens.teams.models

import com.example.aroundcompose.ui.common.enums.Teams

sealed class TeamsViewState {
    data class RestoreSelectedTeams(val team: Teams = Teams.INIT) : TeamsViewState()
    object EnableNextBtn : TeamsViewState()
}