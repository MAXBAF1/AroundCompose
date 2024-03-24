package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class AuthorizationViewState {
    data class RestoreInputsData(val inputsText: Map<FieldType, String> = mapOf()) :
        AuthorizationViewState()

    object EnableLoginBtn : AuthorizationViewState()
    sealed class Errors {
        object EmailExist : AuthorizationViewState()
        object ServiceUnavailable : AuthorizationViewState()
    }
}