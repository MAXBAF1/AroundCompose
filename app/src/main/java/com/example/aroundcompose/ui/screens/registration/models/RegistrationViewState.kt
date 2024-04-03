package com.example.aroundcompose.ui.screens.registration.models

data class RegistrationViewState(
    val loginValue: String = "",
    val emailValue: String = "",
    val passwordValue: String = "",
    val confirmPasswordValue: String = "",
    val isEnabledNextBtn: Boolean = false
)