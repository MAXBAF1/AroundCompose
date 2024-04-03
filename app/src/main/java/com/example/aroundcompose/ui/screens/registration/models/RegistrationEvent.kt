package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.FieldType

sealed class RegistrationEvent {
    data class ChangeFieldText(val type: FieldType, val text: String) : RegistrationEvent()
    object ClickNextBtn : RegistrationEvent()
}