package com.example.aroundcompose.ui.screens.teams.models

import com.example.aroundcompose.ui.common.enums.Teams

sealed class TeamsEvent {
    data class ChangeTeam(val team: Teams) : TeamsEvent()
    data object ClickNextBtn : TeamsEvent()
}