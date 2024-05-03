package com.example.aroundcompose.ui.screens.authorization.models

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.common.models.IFields

data class AuthFields(
    var email: FieldData = FieldData(), var password: FieldData = FieldData()
) : IFields {

    override operator fun get(type: FieldType): FieldData {
        return when (type) {
            FieldType.EMAIL -> email
            FieldType.PASSWORD -> password
            FieldType.LOGIN -> TODO()
            FieldType.CONFIRM_PASSWORD -> TODO()
        }
    }

    override fun set(type: FieldType, value: FieldData) {
        when (type) {
            FieldType.EMAIL -> this.email = value
            FieldType.PASSWORD -> this.password = value
            FieldType.LOGIN -> TODO()
            FieldType.CONFIRM_PASSWORD -> TODO()
        }
    }

    override fun toList(): List<FieldData> = listOf(email, password)
}