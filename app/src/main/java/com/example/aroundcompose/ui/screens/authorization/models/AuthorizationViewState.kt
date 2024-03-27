package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class AuthorizationViewState {
    data class RestoreFieldsData(
        val fieldsText: Map<FieldType, String> = mapOf(
            FieldType.EMAIL to "",
            FieldType.PASSWORD to ""
        )
    ) : AuthorizationViewState()

    object EnableLoginBtn : AuthorizationViewState()
    sealed class Errors {
        object EmailNotExist : AuthorizationViewState()
        object ServiceUnavailable : AuthorizationViewState()
        data class ValidateErrors(val type: FieldType, val text: String) : AuthorizationViewState()
    }
}