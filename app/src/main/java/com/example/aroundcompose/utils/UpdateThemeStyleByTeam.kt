package com.example.aroundcompose.utils

import androidx.compose.runtime.Composable
import com.example.aroundcompose.ui.common.enums.Teams
import com.example.aroundcompose.ui.theme.JetAroundStyle
import com.example.aroundcompose.ui.theme.LocalSettingsEventBus

@Composable
fun UpdateThemeStyleByTeam(team: Teams) {
    val settingsEventBus = LocalSettingsEventBus.current

    when (team) {
        Teams.BLUE -> settingsEventBus.updateStyle(JetAroundStyle.Blue)
        Teams.LIGHT_BLUE -> settingsEventBus.updateStyle(JetAroundStyle.LightBlue)
        Teams.YELLOW -> settingsEventBus.updateStyle(JetAroundStyle.Yellow)
        Teams.PURPLE -> settingsEventBus.updateStyle(JetAroundStyle.Purple)
        else -> {}
    }
}