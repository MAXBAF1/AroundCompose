package com.example.aroundcompose.ui.screens.teams.models

import com.example.aroundcompose.ui.screens.registration.models.RegistrationFields

sealed class TeamsEvent {
    data class GetRegistrationInfo(val fields: RegistrationFields) : TeamsEvent()
    data class ChangeTeam(val teamId: Int) : TeamsEvent()
    data object ClickBtnNext : TeamsEvent()
}