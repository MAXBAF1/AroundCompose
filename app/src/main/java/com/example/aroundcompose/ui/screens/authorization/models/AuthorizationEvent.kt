package com.example.aroundcompose.ui.screens.authorization.models

import androidx.credentials.GetCredentialResponse
import com.example.aroundcompose.ui.common.enums.FieldType

sealed class AuthorizationEvent {
    data class ChangeFieldText(val type: FieldType, val text: String) : AuthorizationEvent()

    data object ClickLoginBtn : AuthorizationEvent()
    data object ClickGoogleBtn : AuthorizationEvent()
    data class HandleGoogleResponse(val result: GetCredentialResponse) : AuthorizationEvent()
    data object ClickLoginVkBtn : AuthorizationEvent()
    data object ClearViewState : AuthorizationEvent()
}