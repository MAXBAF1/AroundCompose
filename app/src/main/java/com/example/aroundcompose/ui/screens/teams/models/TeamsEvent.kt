package com.example.aroundcompose.ui.screens.teams.models

sealed class TeamsEvent {
    data class ChangeTeam(val teamId: Int) : TeamsEvent()
    data object ClickNextBtn : TeamsEvent()
}