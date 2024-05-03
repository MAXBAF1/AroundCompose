package com.example.aroundcompose.ui.screens.authorization.models


data class AuthorizationViewState(
    val fields: AuthFields = AuthFields(),
    val isEnabledLoginBtn: Boolean = false
)