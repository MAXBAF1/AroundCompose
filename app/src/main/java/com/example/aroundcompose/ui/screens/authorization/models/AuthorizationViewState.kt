package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.Teams


data class AuthorizationViewState(
    val fields: AuthFields = AuthFields(),
    val isEnabledLoginBtn: Boolean = false,
    val toNextScreen: Boolean = false,
    val userTeam: Teams = Teams.NONE
)