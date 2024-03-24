package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.ErrorStatus
import com.example.aroundcompose.ui.common.enums.InputType

sealed class RegistrationViewState {
    data class RestoreInputsData(val inputsText: Map<InputType, String> = mapOf()) :
        RegistrationViewState()

    object EnableNextBtn : RegistrationViewState()
    sealed class Errors {
        object EmailExist : RegistrationViewState()
        object ServiceUnavailable : RegistrationViewState()
        data class ValidateErrors(
            val type: InputType,
            val errorStatuses: Map<String, ErrorStatus>,
        ) : RegistrationViewState()
    }
}