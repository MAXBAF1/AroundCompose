package com.example.aroundcompose.ui.screens.settings.models

sealed class SettingsEvent {
    data object ExitFromAccount: SettingsEvent()
}