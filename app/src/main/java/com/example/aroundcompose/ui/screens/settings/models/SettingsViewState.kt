package com.example.aroundcompose.ui.screens.settings.models

import com.example.aroundcompose.data.models.UserDTO

data class SettingsViewState(
    val myInfo: UserDTO = UserDTO(),
    val toAuthorizationScreen: Boolean = false
)