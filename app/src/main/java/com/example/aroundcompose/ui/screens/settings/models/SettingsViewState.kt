package com.example.aroundcompose.ui.screens.settings.models

import com.example.aroundcompose.data.models.UserDTO

data class SettingsViewState(
    val meInfo: UserDTO = UserDTO(),
    val toAuthorizationScreen: Boolean = false
)