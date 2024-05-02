package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData

data class RegistrationViewState(
    val mapOfFields: RegistrationFields = RegistrationFields(),
    val isEnabledNextBtn: Boolean = false
)