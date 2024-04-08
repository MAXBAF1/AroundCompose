package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData


data class AuthorizationViewState(
    val mapOfFields: Map<FieldType, FieldData> = mapOf(
        FieldType.EMAIL to FieldData(),
        FieldType.PASSWORD to FieldData(),
    ),
    val isEnabledLoginBtn: Boolean = false
)