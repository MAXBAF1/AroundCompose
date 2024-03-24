package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class AuthorizationEvent {
    data class InputTextChange(val type: FieldType, val text: String) : AuthorizationEvent()
    object RestoreInputs : AuthorizationEvent()

    object ClickLoginBtn : AuthorizationEvent()
    object ClickLoginGoogleBtn : AuthorizationEvent()
    object ClickLoginVkBtn : AuthorizationEvent()
    object ClickRegistrationBtn : AuthorizationEvent()
    object ClickForgotPasswordBtn : AuthorizationEvent()
}