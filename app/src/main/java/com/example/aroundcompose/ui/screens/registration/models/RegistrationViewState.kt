package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData

data class RegistrationViewState(
    val mapOfFields: Map<FieldType, FieldData> = mapOf(
        FieldType.LOGIN to FieldData(),
        FieldType.EMAIL to FieldData(),
        FieldType.PASSWORD to FieldData(),
        FieldType.CONFIRM_PASSWORD to FieldData()
    ),
//    val loginValue: String = "",
//    val emailValue: String = "",
//    val passwordValue: String = "",
//    val confirmPasswordValue: String = "",
    val isEnabledNextBtn: Boolean = false
)