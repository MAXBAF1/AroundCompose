package com.example.aroundcompose.utils

import com.example.aroundcompose.ui.common.enums.FieldType
import com.example.aroundcompose.ui.common.models.FieldData
import com.example.aroundcompose.ui.common.models.IFields
import com.example.aroundcompose.ui.screens.registration.models.validation_data.ErrorStatus

object TextFieldValidation {
    fun checkLength(text: String, type: FieldType): ErrorStatus {
        if (text.isEmpty()) return ErrorStatus.INITIAL

        return if (type == FieldType.LOGIN) {
            if (text.length in 4..15) ErrorStatus.INITIAL else ErrorStatus.ERROR
        } else {
            if (text.length >= 8) ErrorStatus.INITIAL else ErrorStatus.ERROR
        }
    }

    fun checkRegex(text: String, regex: Regex): ErrorStatus {
        return if (text.isEmpty() || text.matches(regex)) ErrorStatus.INITIAL else ErrorStatus.ERROR
    }

    fun checkPasswordConfirm(password: String, confirmPassword: String): ErrorStatus {
        return if (confirmPassword == password) {
            ErrorStatus.INITIAL
        } else ErrorStatus.ERROR
    }

    fun isAllFieldsValid(fields: IFields): Boolean {
        return fields.toList().all { fieldData ->
            fieldData.fieldText.isNotEmpty() && fieldData.textErrorId == null
        }
    }
}