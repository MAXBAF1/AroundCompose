package com.example.aroundcompose.ui.screens.teams.models

import com.example.aroundcompose.ui.common.enums.Teams

sealed class TeamsEvent {
    data class ChangeTeam(val teams: Teams) : TeamsEvent()
    object ClickBtnNext : TeamsEvent()
}