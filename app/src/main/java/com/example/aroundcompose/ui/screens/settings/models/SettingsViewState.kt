package com.example.aroundcompose.ui.screens.settings.models

import com.example.aroundcompose.data.models.SettingsDTO
import com.example.aroundcompose.data.models.UserDTO

data class SettingsViewState(
    val myInfo: UserDTO = UserDTO(),
    val settingsInfo: SettingsDTO = SettingsDTO(),
    val toAuthorizationScreen: Boolean = false
)