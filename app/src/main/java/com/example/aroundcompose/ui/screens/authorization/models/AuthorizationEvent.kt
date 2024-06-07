package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class AuthorizationEvent {
    data class ChangeFieldText(val type: FieldType, val text: String) : AuthorizationEvent()

    data object ClickLoginBtn : AuthorizationEvent()
    data object ClickLoginGoogleBtn : AuthorizationEvent()
    data object ClickLoginVkBtn : AuthorizationEvent()
    data object ClearViewState : AuthorizationEvent()
}