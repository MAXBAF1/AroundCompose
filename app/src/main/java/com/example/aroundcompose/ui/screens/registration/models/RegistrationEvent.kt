package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class RegistrationEvent {
    data class InputTextChange(val type: FieldType, val text: String) : RegistrationEvent()
    object RestoreInputs : RegistrationEvent()

    object ClickNextBtn : RegistrationEvent()
    object ClickBackBtn : RegistrationEvent()
}