package com.example.aroundcompose.ui.screens.registration.models

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.common.models.IFields

data class RegistrationFields(
    var username: FieldData = FieldData(),
    var email: FieldData = FieldData(),
    var password: FieldData = FieldData(),
    var confirmPassword: FieldData = FieldData(),
) : IFields {

    override operator fun get(type: FieldType): FieldData {
        return when (type) {
            FieldType.LOGIN -> username
            FieldType.EMAIL -> email
            FieldType.PASSWORD -> password
            FieldType.CONFIRM_PASSWORD -> confirmPassword
        }
    }

    override fun set(type: FieldType, value: FieldData) {
        when (type) {
            FieldType.LOGIN -> this.username = value
            FieldType.EMAIL -> this.email = value
            FieldType.PASSWORD -> this.password = value
            FieldType.CONFIRM_PASSWORD -> this.confirmPassword = value
        }
    }

    override fun toList(): List<FieldData> = listOf(username, email, password, confirmPassword)
}