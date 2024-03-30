package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class AuthorizationViewState {
    data class RestoreData(
        val fieldsText: Map<FieldType, String> = mapOf(
            FieldType.EMAIL to "",
            FieldType.PASSWORD to ""
        )
    ) : AuthorizationViewState()

    data class FieldsTextChanged(
        val fieldsText: Map<FieldType, String> = mapOf(
            FieldType.EMAIL to "",
            FieldType.PASSWORD to ""
        )
    ) : AuthorizationViewState()

    object EnableLoginBtn : AuthorizationViewState()
    sealed class Errors {
        object EmailNotExist : AuthorizationViewState()
        object ServiceUnavailable : AuthorizationViewState()
    }
}