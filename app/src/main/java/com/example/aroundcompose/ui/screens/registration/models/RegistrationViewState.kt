package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.ErrorStatus
import com.example.aroundcompose.ui.common.enums.FieldType

sealed class RegistrationViewState {
    data class RestoreInputsData(val inputsText: Map<FieldType, String> = mapOf()) :
        RegistrationViewState()

    object EnableNextBtn : RegistrationViewState()
    sealed class Errors {
        object EmailExist : RegistrationViewState()
        object ServiceUnavailable : RegistrationViewState()
        data class ValidateErrors(
            val type: FieldType,
            val errorStatuses: Map<String, ErrorStatus>,
        ) : RegistrationViewState()
    }
}