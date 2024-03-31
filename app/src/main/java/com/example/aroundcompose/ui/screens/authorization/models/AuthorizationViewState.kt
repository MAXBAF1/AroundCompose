package com.example.aroundcompose.ui.screens.authorization.models


data class AuthorizationViewState(
    val emailValue: String = "",
    val passwordValue: String = "",
    val isEnabledLoginBtn: Boolean = false
)