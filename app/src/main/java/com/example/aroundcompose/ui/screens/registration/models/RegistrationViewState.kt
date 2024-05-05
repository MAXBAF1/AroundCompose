package com.example.aroundcompose.ui.screens.registration.models

data class RegistrationViewState(
    val fields: RegistrationFields = RegistrationFields(),
    val isEnabledNextBtn: Boolean = false,
    val toNextScreen: Boolean = false
)